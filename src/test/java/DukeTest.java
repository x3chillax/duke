
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DukeTest
{
    @Test
    public void dummyTest()
    {
        assertEquals(2,2);
    }
    @Test
    public void TestIcon()
    {
        try
        {
            assertEquals("✘", new Deadline("testing", "20/12/2013").getStatusIcon());
        } catch (DukeException e)
        {
            e.printStackTrace();
        }
    }

}