import java.util.ArrayList;
import java.util.Scanner;

public class Duke
{
    public static void main(String[] args)
    {
        String line = "     ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        Scanner input = new Scanner(System.in);
        ArrayList<Task> arlist = new ArrayList<Task>();
        int i = 0;
        String[] number;
        while(true)
        {
            String command = input.nextLine();
            number = command.split(" ", 2);
            switch(number[0])
            {
                case "bye":
                {
                    System.out.print(line);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                }
                case "list":
                {
                    System.out.print(line);
                    for (int j = 0; j < i; j += 1)
                    {
                        System.out.println("      " + (j + 1) + ". " + "[" + arlist.get(j).getStatusIcon() + "]" + arlist.get(j).getDescription());
                    }
                    System.out.println(line);
                    break;
                }
                case "done":
                {
                    int placeholder = (Integer.parseInt(number[1]) - 1);
                    arlist.get(placeholder).markAsDone();
                    System.out.print(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + "[" + arlist.get(placeholder).getStatusIcon() + "]" + arlist.get(placeholder).getDescription());
                    System.out.println(line);
                    break;
                }
                default:
                {
                    System.out.print(line);
                    System.out.println("     added: " + command);
                    System.out.println(line);
                    arlist.add(new Task(command));
                    i += 1;
                }
            }
            if(command.equals("bye"))
            {
                break;
            }
        }
    }
}
