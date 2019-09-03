import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task
{

    protected String by;
    LocalDateTime dt = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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


