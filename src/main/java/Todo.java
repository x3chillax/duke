public class Todo extends Task
{

    //protected String by;

    public Todo(String description)//, String by)       //todo is only a string. no need date and time
    {
        super(description);
        //this.by = by;
    }

    @Override
    public String toString()
    {
        return "[T]" + super.toString();
    }
}