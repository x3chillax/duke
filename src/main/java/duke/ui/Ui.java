package duke.ui;

import duke.Duke;
import duke.dish.Dish;
import duke.ingredient.Ingredient;
import duke.ingredient.IngredientsList;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents the user interaction, used for getting the user input and printing the output on the screen.
 */
public class Ui {

    private Scanner scanner;
    private static final String line = "_________________________________________________________________________________________";

    private final boolean DRAW = false;

    /**
     * The constructor method for Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the input entered by the user.
     *
     * @return String the input entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Show a line.
     */
    public void showLine() {
        System.out.println("\t " + line);
    }

    public void chefDrawing() {
        if(!DRAW) return;
        System.out.println("                           (c)___c____(c)           ");
        System.out.println("                            \\ ........../          ");
        System.out.println("                             |.........|            ");
        System.out.println("                              |.......|             ");
        System.out.println("                              |.......|             ");
        System.out.println("                              |=======|             ");
        System.out.println("                              |=======|             ");
        System.out.println("                             __o)''''::?            ");
        System.out.println("                            C__    c)::;            ");
        System.out.println("                               >--   ::     /\\     ");
        System.out.println("                               (____/      /__\\    ");
        System.out.println("                               } /''|      |##|     ");
        System.out.println("                    __/       (|V ^ )\\     |##|    ");
        System.out.println("                    o | _____/ |#/ / |     |##|     ");
        System.out.println("           @        o_|}|_____/|/ /  |     |##|     ");
        System.out.println("                          _____/ /   |     ~!!~     ");
        System.out.println("              ======ooo}{|______)#   |     /`'\\    ");
        System.out.println("          ~~~~ ;    ;          ###---|8     ''      ");
        System.out.println("        ____;_____;____        ###====     /:|\\    ");
        System.out.println("       (///0///@///@///)       ###@@@@|             ");
        System.out.println("       |~~~~~~~~~~~~~~~|       ###@@@@|             ");
        System.out.println("        \\             /        ###@@@@|               +          ");
        System.out.println("         \\___________/         ###xxxxx      /\\      //         ");
        System.out.println("           H H   H  H          ###|| |      /  \\    //           ");
        System.out.println("           H H   H  H           | || |     /____\\  /~_^_         ");
        System.out.println("           H H   H  H           C |C |     _|@@|_ /__|#|_         ");
        System.out.println("           H H   H  H            || ||    /_|@@|_/___|#|/|        ");
        System.out.println(" v    \\/   H(o) (o) H            || ::   |:::::::::::::|#|       ");
        System.out.println(" ~    ~~  (o)      (o)        Ccc__)__)   |   CHEF      |#|       ");
        System.out.println("  \\|/      ~   @* & ~                    |:::::::::::::|/  \\|/  ");
        System.out.println("   ~           \\|/        !!        \\ !/  ~~~~~~~~~~~~~    ~~~  ");
        System.out.println("               ~~~        ~~         ~~           ~~              ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   ");
    }

    public void dishDrawing() {
        if(!DRAW) return;
        System.out.println("            (\\                                      ");
        System.out.println("             \\ \\                                   ");
        System.out.println("               \\/ ___,.-------..__                  ");
        System.out.println("        //\\\\ _,-'\\\\               `'--._ //\\\\  ");
        System.out.println("        \\\\ ;'      \\\\                   `: //    ");
        System.out.println("         `(          \\\\                   )'       ");
        System.out.println("           :.          \\\\,----,         ,;         ");
        System.out.println("            `.`--.___   (    /  ___.--','            ");
        System.out.println("              `.     ``-----'-''     ,'              ");
        System.out.println("                 -.               ,-                 ");
        System.out.println("                     `-._______.-                    ");
        System.out.println("\n");
    }

    /**
     * Used to print the greeting message from {@link Duke}.
     */
    public void showWelcome() {
        showLine();
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (timeOfDay < 12) {
            greeting = "Good Morning";
        } else if (timeOfDay < 16) {
            greeting = "Good Afternoon";
        } else if (timeOfDay < 21) {
            greeting = "Good Evening";
        } else {
            greeting = "Good Night";
        }
        System.out.println("\t " + greeting + " chef! I'm Duke");
    }

    public void showHasExpiring() {
        System.out.println("\t A gentle reminder you have some  expired ingredients in the fridge");
        System.out.println("\t Would you like to see the list?");
    }

    public void showOptions() {
        System.out.println("\t Options (choose one): ");
        System.out.println("\t 'a' remove all expiring");
        System.out.println("\t 'b' add/remove/use an ingredient");
        System.out.println("\t 'c' place/remove/change an order");
        System.out.println("\t 'd' add/remove/change a dish");
        System.out.println("\t 't' view today's todo list");
        System.out.println("\t 'q' to exit");
    }

    public void showUsed(Ingredient ingredient) {
        System.out.println("Great, just used " + ingredient);
    }

    public void show(String message) {
        System.out.println("\t " + message);
    }

    public void showIngredientTask() {
        showIngredientTemplate();
        System.out.println("\t type 'back' to go back to the main menu");
        System.out.println("\t type 'show' to see all ingredients currently in the fridge");
        System.out.println("\t type 'listtoday to see all ingredients that have expired today");
        System.out.println("\t type 'template' to see the format of the commands");
    }

    public void showIngredientTemplate(){
        System.out.println("\t Continue by adding, removing,using or finding an ingredient. \n\t");
        System.out.println("\t You can also edit the Name or Amount of an ingredient(using its index number). \n\t Template: ");
        showLine();
        System.out.println("\t To add, add <Ingredient name> <Amount> <expiry date: DD/MM/YYYY>");
        System.out.println("\t To remove, remove <Ingredient number>");
        System.out.println("\t To use, use <Ingredient name> <Amount> *always use most recently expiring ingredients first, to prevent food waste!*");
        System.out.println("\t To change amount, changeamount <Ingredient's Index> <New amount>");
        System.out.println("\t To change name, changename <Ingredient's Index> <New name>");
        System.out.println("\t To find an ingredient, find <Ingredient name>");
        showLine();
    }

    public void showDishTemplate() {
        dishDrawing();
        showLine();
        System.out.println("\t Continue by adding, removing, listing, adding ingredient and initializing \n\t Template: ");
        showLine();
        System.out.println("\t add <dish name>");
        System.out.println("\t remove <dish index>");
        System.out.println("\t list");
        System.out.println("\t find <description>");
        System.out.println("\t change <index> <description>");
        System.out.println("\t ingredient <description> <amount> <index>");
        System.out.println("\t initialize (REMOVES all entries in the list)");
        System.out.println("\t back, return to main menu");
        System.out.println("\t template");
        showLine();
    }

    public void showOrderTemplate() {
        showLine();
        System.out.println("\t Continue by adding, removing, altering, listing order and initializing order list. \n\t Command Template: ");
        showLine();
        System.out.println("\t init");
        System.out.println("\t add [-d ORDER_DATE-(dd/mm/yyyy)] -n DISH1_NAME[*DISH_AMOUNT], DISH2_NAME[*DISH_AMOUNT]");
        System.out.println("\t alter ORDER_INDEX ORDER_DATE-(dd/mm/yyyy)");
        System.out.println("\t remove ORDER_INDEX");
        System.out.println("\t done ORDER_INDEX");
        System.out.println("\t list [-l LIST_TYPE-(option: all (default) | undone | today | undoneToday)]");
        System.out.println("\t list -n DISH_NAME    *** Find the dishes in today's undone orders ***");
        System.out.println("\t list -d ORDER_DATE-(dd/mm/yyyy) [-l LIST_TYPE-(option: all (default) | undone)]");
        showLine();
    }

    public void showIngredientsInFridge(IngredientsList ingredientsList) {
        if (ingredientsList.isEmpty())
            System.out.println("\t The fridge is empty, better go buy some ingredients! ");
        else {
            System.out.println("\t Here is a list of all the ingredients in your fridge: ");
            int i = 1;
            for (Ingredient ingredient : ingredientsList.sortByExpiryDate().getAllEntries()) {
                System.out.println(i + ": " + ingredient);
                i++;
            }
        }
    }

    /**
     * Show loading error.
     */
    public void showLoadingError() {
        System.out.println("\t ☹ OOPS!!! Error while loading the list from the hard disc");
    }

    /**
     * Show the error to user.
     *
     * @param e an error
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Show the task to user.
     *
     * @param task string
     */
    public void showTask(String task) {
        System.out.println(task);
    }

    /**
     * Show that this task is marked.
     *
     * @param doneOrder The description of the order that is marked as done
     */
    public void showMarkDoneOrder(String doneOrder) {
        System.out.println("\t Nice! I've marked this order as done:");
        System.out.println("\t " + doneOrder);
    }

    /**
     * Show that this task is marked.
     *
     * @param task The description of the task that is marked as done
     */
    public void showMarkDone(String task) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + task);
    }

    /**
     * Show the order that has been changed serving date.
     *
     * @param date         the newly set date for serving the order
     * @param changedOrder the order that has been changed
     */
    public void showOrderChangedDate(String date, String changedOrder) {
        System.out.println("\t Nice! I've changed the order at " + date + ":");
        System.out.println("\t " + changedOrder);
    }

    /**
     * Show the size of the list.
     *
     * @param size the size
     */
    public void showSize(int size) {
        System.out.print("\t Now you have " + size);
        if (size == 1) {
            System.out.print(" task");
        } else {
            System.out.print(" tasks");
        }
        System.out.println(" in the list.");
    }

    /**
     * Show the size of the order list.
     *
     * @param size the size
     */
    public void showOrderListSize(int size) {
        System.out.print("\t Now you have " + size);
        if (size == 1) {
            System.out.print(" order");
        } else {
            System.out.print(" orders");
        }
        System.out.println(" in the order list.");
    }

    /**
     * Shows that a task has been added.
     *
     * @param command ay
     * @param size    ya
     */
    public void showAddCommand(String command, int size) {
        System.out.println("\t Got it. I've added this: ");
        System.out.println("\t " + command);
        // showSize(size);
    }

    /**
     * Shows that a order has been added.
     *
     * @param description information of the order be added
     * @param size  current size of the whole order list
     */
    public void showAddOrder(String description, int size) {
        System.out.println("\t Got it. I've added this order: ");
        System.out.println("\t " + description);
        showOrderListSize(size);
    }

    /**
     * Show the task that has been removed.
     *
     * @param removed the task
     * @param size    size of list
     */
    public void showRemovedIngredient(String removed, int size) {
        System.out.println("\t Noted. I've removed this ingredient:");
        System.out.println("\t " + removed);
        showSize(size);
    }

    /**
     * Show the order that has been removed.
     *
     * @param removed the order
     * @param size    size of order list
     */
    public void showRemovedOrder(String removed, int size) {
        System.out.println("\t Noted. I've removed this order:");
        System.out.println("\t " + removed);
        showOrderListSize(size);
    }

    public void showAddedDishes(String dish) {
        showLine();
        System.out.println("\t you have added the following dish: ");
        System.out.println("\t " + dish);
        showLine();
    }

    public void showAddedIngredient(String ingredient) {
        System.out.println("\t you have added the following ingredient: ");
        System.out.println("\t " + ingredient);
    }

    public void showDishes(String Dish, int Nb) {
        System.out.println(Dish + "\t orders: " + Nb);
    }

    public void showDeletedDIsh(String dish) {
        showLine();
        System.out.println("\t The following dish have been removed:");
        System.out.println("\t " + dish);
        showLine();
    }

    public void showIngredients(Ingredient ingredient, Dish dish) {
        showLine();
        System.out.println("\t ingredient: " + ingredient.getName()
                + "\n\t added to: " + dish.getDishname());
        showLine();
    }

    /**
     * WARNING: DO NOT USE THIS METHOD as it is not compatible with Mac comp.
     * Every time this method is executed, it clears the console screen.
     * @throws IOException
     * @throws InterruptedException
     */
    public void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
