public class Todo extends Task
{
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