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
                if ((input.isEmpty()) || (input == null) || (input.trim().isEmpty()))
                {
                    continue;
                }
                userCommand = input.trim().split("\\s+");

                ACommand command = commandManager.getCommand(userCommand[0]);

                if (command != null)
                {
                    command.launch(userCommand);
                }
                else
                {
                    System.out.println("-----=[ <" + userCommand[0] + "> not found use <help> ]=-----");
                }
            }
            while (true);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("-----=[ end of input reached ]=-----");
        }
        finally
        {
            scanner.close();
        }
    }
}
