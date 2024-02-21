package Commands;


import Tools.CommandManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// пустая строка не считается за ошибку
// если команда начинается с пробела, то пробел удалится
// все неточности и ошибки в скрипте информируются и выводятся в консоль
public class execute_script extends ACommand
{
    CommandManager commandManager;
    Scanner scanner;
    public execute_script(CommandManager commandManager, Scanner scanner)
    {
        super("execute_script [file_path]", "read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively");
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    @Override
    public boolean launch(String[] command) throws IOException
    {
        if(command.length == 2)
        {

            String filePath = command[1];
            File file = new File(filePath);

            if (!file.exists())
            {
                System.out.println("File <" + filePath + "> doesnt exists: ");
                return false;
            }
            if (!file.isFile())
            {
                System.out.println("Cannot open File <" + filePath + ">");
                return false;
            }
            if (!file.canRead())
            {
                System.out.println("Cannot read File <" + filePath + ">");
                return false;
            }
            if (file.length() == 0)
            {
                System.out.println("File <" + filePath + "> is empty");
                return false;
            }

            ACommand commandFromScript; // экземпляр команды
            List<String> lines;         // строки в скрипте
            List<String> validCommands = new ArrayList<>();
            lines = Files.readAllLines(Paths.get(filePath)); // notUsed
            int count_of_error_commands_in_script = 0;
            boolean continue_without_errors = false;

            for (String line : lines)
            {
                if (line.trim().isEmpty())
                {
                    continue;
                }
                String[] wordsInLine = line.split("\\s+");
                commandFromScript = commandManager.getCommand(wordsInLine[0]);
                if (wordsInLine[0].equals("execute_script"))
                {
                    System.out.println("Сommand <execute_script> was found. To prevent recursion, the script was not used");
                    System.out.println("Remove command <execute_script> from the script to avoid recursion");
                    return false;
                }
                if (commandFromScript == null)
                {
                    System.out.println("Seems command <" + wordsInLine[0] + "> from script not exists");
                    count_of_error_commands_in_script += 1;
                }
                else
                {
                    validCommands.add(line);
                }
            }

            if (count_of_error_commands_in_script != 0)
            {
                System.out.println("You have <" + count_of_error_commands_in_script + "> error commands in your script");
                System.out.print("Do you want to execute script without error commands? [Y/N]: ");
                String ans = scanner.nextLine();
                continue_without_errors =  ans.equalsIgnoreCase("Y") ||
                                        ans.equalsIgnoreCase("Yes") ||
                                        ans.equals("+") || ans.equals("Da");
            }
            if (continue_without_errors)
            {
                System.out.println("-----=[ Starting script execution ]=-----");

                for (String line : lines)
                {
                    line = line.trim();
                    String[] wordsInLine = line.split("\\s+");
                    commandFromScript = commandManager.getCommand(wordsInLine[0]);
                    if (commandFromScript != null)
                    {
                        commandFromScript.launch(wordsInLine);
                    }
                    else
                    {
                        System.out.println("Seems command <" + wordsInLine[0] + "> not exists. Try to use <help> to get information about all commands");
                    }
                }
                return true;
            }
            else
            {
                System.out.println("-----=[ Stopping script execution ]=-----");
                return false;
            }

        }
        else
        {
            System.out.println("command <" + getName() + "> expect 1 argument:");
            System.out.println(getName() + " [ script_path ]");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }
    }


}
