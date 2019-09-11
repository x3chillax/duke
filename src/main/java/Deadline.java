import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class defines the "Deadline" type Task
 *
 * @author Joey Goh
 * @see Task
 */
public class Deadline extends Task
{
    protected String by;
    LocalDateTime dt = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * method to create a new Deadline object
     *
     * @param description the description of the Deadline
     * @param by the deadline of the Deadline
     */
    public Deadline(String description, String by) throws DukeException
    {
        super(description);     //return book
        this.by = by;           //Sunday
        //String str = "2007-32-32T70:70:30";
        //LocalDateTime dt = LocalDateTime.now();
        try
        {
            dt = LocalDateTime.parse(by, formatter);
        }
        catch(Exception E)
        {
            //System.out.println("die liao la");
            throw new DukeException("Please enter in the following syntax: deadline (task) /by YYYY-MM-DD HH:MM");
        }
    }

    @Override
    public String toString()
    {
        return "[D]" + super.toString() + "(by: " + dt.format(formatter) + ")";
    }
}


