package duke.command.dishesCommand;

import duke.command.Command;
import duke.dish.DishList;
import duke.exception.DukeException;
import duke.fridge.Fridge;
import duke.order.OrderList;
import duke.storage.FridgeStorage;
import duke.storage.OrderStorage;
import duke.ui.Ui;

public class ResetDishCommand extends Command {

    /**
     * this method clears the list, the program asks the user whether they want the list to be cleared
     * user can enter y or n, yes or no.
     *
     * @param fridge
     * @param dishList
     * @param ol
     * @param ui
     * @param fs
     * @param os
     * @throws DukeException
     *
     * @author Hafidz
     */
    @Override
    public void execute(Fridge fridge, DishList dishList, OrderList ol, Ui ui, FridgeStorage fs, OrderStorage os) throws DukeException {
        System.out.println("\t are you sure you want to clear list? [y/n]");
        String command = ui.readCommand();
        if(command.toLowerCase().equals("y")){
            dishList.clearList();
            System.out.println("\t LIST IS CLEARED");
        }
        else if(command.toLowerCase().equals("n")){
            System.out.println("\t LIST IS NOT CLEARED");
        }
        else {
            System.out.println("\t LIST IS NOT CLEARED");
            throw new DukeException("Please enter y or n after 'initialize' command");
        }
    }
}
