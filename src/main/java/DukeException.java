/**
 * This class defines the "DukeException" type Exception
 *
 * @author Joey Goh
 * @see Exception
 */
public class DukeException extends Exception
{
    /**
     * method to create a new DukeException object
     *
     * @param message the message of the exception
     */
    public DukeException(String message)
    {
        super(message);
    }
}