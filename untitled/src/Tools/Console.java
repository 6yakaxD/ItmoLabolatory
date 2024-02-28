package Tools;

import Commands.ACommand;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console
{
    public final String userMsg = "> ";
    private Scanner scanner;
    private CommandManager commandManager;

    public Console(Scanner scanner, CommandManager commandManager)
    {
        this.scanner = scanner;
        this.commandManager = commandManager;
    }

    public void interactiveModeInit() throws IOException {
        String[] userCommand;
        try
        {
            do
            {
                System.out.print(userMsg);

                String input = scanner.nextLine();
                if (input.isEmpty())
                {
                    continue;
                }
                userCommand = input.split("\\s+");

                ACommand command = commandManager.getCommand(userCommand[0]);

                if (command != null)
                {
                    command.launch(userCommand);
                }
                else
                {
                    System.out.println("Seems command <" + userCommand[0] + "> not exists. Try to use <help> to get information about all commands");
                }
            }
            while (true);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("End of input reached.");
        }
        finally
        {
            scanner.close();
        }
    }
}
