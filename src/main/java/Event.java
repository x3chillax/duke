public class Event extends Task
{

    protected String time;

    public Event(String description, String time) //project meeting /at Mon 2-4pm
    {
        super(description);     //Task's description
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}