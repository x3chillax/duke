package duke.command.ingredientCommand;

import duke.command.Command;
import duke.dish.DishList;
import duke.exception.DukeException;
import duke.ingredient.IngredientsList;
import duke.order.OrderList;
import duke.storage.FridgeStorage;
import duke.storage.OrderStorage;
import duke.ui.Ui;

/**
 * Represents a specific {@link Command} used to list all the Ingredients in the {@link IngredientsList}.
 */
public class ListCommand extends Command {

    @Override
    public void execute(IngredientsList il, DishList dl, OrderList ol, Ui ui, FridgeStorage fs, OrderStorage os) throws DukeException {
        if (il.size() == 0) {
            throw new DukeException("No ingredients yet!");
        } else {
            System.out.println("\t Here are the ingredients in your list:");
            for (int i = 1; i <= il.size(); i++) { // looping to print all the saved tasks
                ui.showTask("\t " + i + "." + il.getEntry(i - 1).toString());
            }
        }
    }
}
