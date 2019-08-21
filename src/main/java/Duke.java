import java.util.ArrayList;
import java.util.Scanner;

public class Duke
{
    public static void main(String[] args)
    {
        String line = "     ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        Scanner input = new Scanner(System.in);
        ArrayList<String> arlist = new ArrayList<String>();
        int i = 0;
        while(true)
        {
            String command = input.nextLine();
            if(command.equals("bye"))
            {
                System.out.print(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if(command.equals("list"))
            {
                System.out.print(line);
                for(int j = 0; j < i; j += 1)
                {
                    System.out.println("      " +  (j+1) + ". " + arlist.get(j));
                }
                System.out.println(line);
            }
            else
            {
                System.out.print(line);
                System.out.println("     " + command);
                System.out.println(line);
                arlist.add(i, command);
                i += 1;
            }
            /*
            else
            {
                System.out.print(line);
                System.out.println("     " + command);
                System.out.println(line);
            }
            */
        }
    }
}
