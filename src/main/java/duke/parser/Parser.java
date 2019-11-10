package duke.parser;

import duke.Duke;
import duke.command.Command;
import duke.command.dishesCommand.*;
import duke.command.ingredientCommand.*;
import duke.command.orderCommand.*;
import duke.dish.Dish;
import duke.exception.DukeException;
import duke.ingredient.Ingredient;
import duke.order.Order;

import java.util.Date;

/**
 * Represents a parser used to parse the input String from the user into a Duke understandable Command.
 * It should deals with making sense of the user command.
 */
public class Parser {

    //There is no constructor method for all others are static.

    /**
     * Returns a {@link Command} that can be understood by {@link Duke} and executed after.
     * We first split the fullCommand into 2, the keyword, followed by everything else.
     * Then we perform switching based on the keyword.
     *
     * @param fullCommand The String command entered by the user
     * @return Command The Command to be executed
     * @throws DukeException for any invalid input
     */
    public static Command parse(String fullCommand, Duke.Type type) throws DukeException {
        switch (type) {
            case INGREDIENT:
                return ingredient(fullCommand);
            case DISH:
                return dish(fullCommand);
            case ORDER:
                return order(fullCommand);
            default:
                throw new DukeException("not a valid type");
        }
    }

    private static Command order(String fullCommand) throws DukeException {
        String[] part = fullCommand.split(" ", 2);
        if (part.length > 4)
            throw new DukeException("must specify ordered dishes and order date");
        else {
            switch (part[0]) {
                case "add":   //add a new order
                    return addOrderParser(part);
                case "alter":  //alter order date
                    return alterOrderDateParser(part);
                case "remove": //fall through onto next case
                case "done":  //remove or done an order
                    return removeOrDoneOrderParser(part);
                case "init":
                    return new InitOrderListCommand();
                case "list": //list orders
                    String[] listType;
                    if (part.length == 1) {
                        listType = "-l all".split(" ", 2);
                    } else {
                        listType = part[1].split(" ", 2);
                    }
                    if (listType.length == 1) {
                        throw new DukeException("Must enter a list type, dishes name, or order date");
                    }
                    return new ListOrderCommand(listType);
                default:
                    throw new DukeException("Not a valid command for an order");
            }
        }
    }

    /**
     * commands for Dish
     * add: adds a dish to dishList
     * remove: remove a dish from dishList
     * list: list all dishes in list
     * initialize: clears the list
     * ingredient: add an ingredient to a dish
     *
     * @param fullCommand command from the user
     * @return a command to be executed
     * @throws DukeException
     * @@@author Hafidz
     */
    private static Command dish(String fullCommand) throws DukeException {
        int index = 0, amount = 0;
        String[] part = fullCommand.split(" ", 2);
        switch (part[0]) {
            case "add":
                if(part.length < 2) {
                    throw new DukeException("specify dish name");
                }
                else {
                    part[1] = part[1].replaceAll("\\s+", " ");
                }
                return new AddDishCommand(new Dish(part[1]));
            case "remove":
                try {
                    return new DeleteDishCommand(checkInt(part[1]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeException("enter a valid index");
                }
            case "list":
                return new ListDishCommand();
            case "initialize":
                return new ResetDishCommand();
            case "ingredient":
                String[] getIng = new String[0];
                try {
                    getIng = part[1].split(" ", 3);
                } catch (Exception e) {
                    throw new DukeException("description cannot be emtpy");
                }
                try {
                    amount = checkInt(getIng[1]);
                    index = checkInt(getIng[2]);
                } catch (Exception e) {
                    throw new DukeException("enter a valid amount/index");
                }
                return new AddIngredient(new Ingredient(getIng[0], amount, new Date()), index);
            case "find":
                try {
                    return new FindDishCommand(part[1]);
                } catch (Exception e) {
                    throw new DukeException("description cannot be emtpy");
                }
            case "change":
                try {
                    String[] partition = part[1].split(" ", 2);
                    index = Integer.parseInt(partition[0]);
                    return new ChangeDishCommand(partition[1], index);
                } catch (NumberFormatException e) {
                    throw new DukeException("enter a valid index/description");
                }
            default:
                throw new DukeException("not a valid command for a Dish");
        }
    }

    private static Command ingredient(String fullCommand) throws DukeException {
        String[] part = fullCommand.split(" ");
        switch (part[0]) {
            case "add":
                if (part.length != 4)
                    throw new DukeException("must specify ingredient name, amount and/or expiry date");
                return new AddCommand(new Ingredient(part[1], checkInt(part[2]), part[3]));
            case "remove":
                if (part.length != 2)
                    throw new DukeException("must specify a index");
                return new DeleteCommand(checkInt(part[1]));
            case "use":
                if (part.length != 3)           //use something 23
                    throw new DukeException("follow the template: use <ingredient name> <amount>");
                return new UseCommand(new Ingredient(part[1], checkInt(part[2]), new Date()));
            case "listtoday":
                if (part.length != 1)
                    throw new DukeException("follow the template: listtoday");
                return new FindToday();
            case "find":
                if (part.length != 2)
                    throw new DukeException("follow the template: find <ingredient name>");
                return new FindIngredientCommand(part[1]);
            case "changename":
                if (part.length != 3)
                    throw new DukeException("follow the template: change <ingredient index> <new ingredient name>");
                return new ChangeNameCommand(checkInt(part[1]), part[2]);       //change 2 beef
            case "changeamount":
            if (part.length != 3)
                throw new DukeException("follow the template: change <ingredient index> <new ingredient amount");
            return new ChangeAmountCommand(checkInt(part[1]), checkInt(part[2]));       //change 2 70
            default:
                throw new DukeException("not a valid command for an Ingredient");
        }
    }

    /**
     * Checks the length of a String array is of size 2.
     *
     * @throws DukeException when array is not of size 2.
     */
    public static void checkLength(String[] str) throws DukeException {
        if (str.length != 2) {
            throw new DukeException("The description cannot be empty.");
        }
    }

    /**
     * Split a string and check its length.
     */
    public static String[] splitAndCheck(String str, String regex) throws DukeException {
        String[] part = str.split(regex, 2);
        checkLength(part); //Throws DukeException
        return part;
    }

    /**
     * Converts a string into a number, and checks if it is out of bounds.
     *
     * @return Returns a valid integer
     * @throws DukeException when it is invalid
     */
    public static int parseInt(String str, int size) throws DukeException {
        int x;
        try {
            //Throws NumberFormatException
            x = Integer.parseInt(str);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        if (x < 0 || x >= size) {
            throw new DukeException("Index is out of bounds.");
        }
        return x;
    }

    public static int checkInt(String str) throws DukeException {
        final int MAX = Integer.MAX_VALUE;
        return parseInt(str, MAX);
    }

    public static Command addOrderParser(String[] splitter) throws DukeException {
        Order newOrder;
        Date orderDate;
        String[] orderedDishes;
        if (splitter[1].startsWith("-n ")) {
            if (splitter[1].length()<4) { throw new DukeException("Must specify dishes name"); }
            newOrder = new Order();
            orderedDishes = splitter[1].substring(3).split(", ");
        } else if (splitter[1].startsWith("-d ")&&splitter[1].length()>20) {
            String[] dateAndDish = splitter[1].substring(3).split(" -n ",2);
            if (dateAndDish[0].length()!=10) { throw new DukeException("Must enter a valid order date: dd/mm/yyyy"); }
            orderDate = Convert.stringToDate(dateAndDish[0]);
            newOrder = new Order(orderDate);
            orderedDishes = dateAndDish[1].split(", ");
        } else { throw new DukeException("must enter a valid order date or specify dishes"); }
        for (String dishes: orderedDishes) {
            String[] dishesSplit = dishes.split("\\*", 2);
            if (dishesSplit.length==1) newOrder.addDish(dishesSplit[0], 1);
            else {
                int dishAmount;
                try{
                    dishAmount = checkInt(dishesSplit[1]);
                    if (dishAmount<=0) {throw new DukeException("Must enter a dishes amount larger than 1");}
                } catch (NumberFormatException e) {
                    throw new DukeException("cannot resolve non-integer or too large dishes amount");
                }
                newOrder.addDish(dishesSplit[0], dishAmount);
            }
        }
        return new AddOrderCommand(newOrder);
    }

    public static Command alterOrderDateParser(String[] splitter) throws DukeException {
        if (splitter.length == 1) {
            throw new DukeException("Must enter an order index.\n\t Note that ORDER_INDEX starts from 1");
        }
        String[] indexAndDate = splitter[1].split(" ", 2);
        int orderIndex;
        Date orderDate;
        try {
            orderIndex = checkInt(indexAndDate[0]);
            if (orderIndex <= 0) throw new DukeException("Must enter a positive order index");
        } catch (NumberFormatException e) {
            throw new DukeException("Must enter a valid order index");
        }
        if (indexAndDate.length == 1) { orderDate = new Date(); }
        else {
            orderDate = Convert.stringToDate(indexAndDate[1]);
            if (orderDate==null) {throw new DukeException("Error when converting order date");}
        }
        return new AlterDateCommand(orderIndex-1, orderDate);
    }

    public static Command removeOrDoneOrderParser(String[] splitter) throws DukeException {
        if (splitter.length == 1) {
            throw new DukeException("Must enter an order index.\n\t Note that ORDER_INDEX starts from 1");
        }
        try {
            int orderIndex = checkInt(splitter[1]);
            if (orderIndex <= 0) throw new DukeException("Must enter a positive order index");
            if (splitter[0].equals("remove")) return new DeleteOrderCommand(orderIndex - 1);
            else return new DoneOrderCommand(orderIndex - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Must enter a valid order index");
        }
    }
}
