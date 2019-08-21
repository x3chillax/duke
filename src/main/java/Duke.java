import java.util.Scanner;

public class Duke
{
    public static void main(String[] args)
    {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String line = "     ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        Scanner input = new Scanner(System.in);
        while(true)
        {
            String command = input.next();
            if(command.equals("bye"))
            {
                System.out.print(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.print(line);
            System.out.println("     " + command);
            System.out.println(line);
        }
    }
}
