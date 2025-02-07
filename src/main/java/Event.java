import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class defines the "Event" type Task
 *
 * @author Joey Goh
 * @see Task
 */
public class Event extends Task
{

    protected String time;
    LocalDateTime dt = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * method to create a new Event object
     *
     * @param description the name of the Event
     * @param time the time of the Event
     */
    public Event(String description, String time) throws DukeException//project meeting /at Mon 2-4pm
    {
        super(description);     //Task's description
        this.time = time;
        try
        {
            dt = LocalDateTime.parse(time, formatter);
        }
        catch(Exception E)
        {
            //System.out.println("die liao la");
            throw new DukeException("Please enter in the following syntax: event (EventName) /at YYYY-MM-DD HH:MM");
        }
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + "(at: " + dt.format(formatter) + ")";
    }
}