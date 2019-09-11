/**
 * This class defines the "Todo" type Task
 *
 * @author Joey Goh
 * @see Task
 */
public class Todo extends Task
{
    /**
     * method to create a new Todo object
     *
     * @param description the description of the Todo
     */
    public Todo(String description) throws DukeException       //todo is only a string. no need date and time
    {

        super(description);
        if(description.trim().isEmpty())            //if description is juz white spaces.
        {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString()
    {
        return "[T]" + super.toString();
    }
}