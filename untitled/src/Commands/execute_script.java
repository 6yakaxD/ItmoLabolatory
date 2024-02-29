package Commands;


import CustomExeptions.*;
import Tools.CollectionManager;
import Tools.CommandManager;

import java.io.*;
import java.util.*;

public class execute_script extends ACommand
{
    CommandManager commandManager;
    Scanner scanner;
    CollectionManager collectionManager;

    public static List<String> scriptStack = new ArrayList<>();

    public execute_script(CommandManager commandManager, Scanner scanner, CollectionManager collectionManager)
    {
        super("execute_script [file_path]", "read and execute the script from the specified file");
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.collectionManager = collectionManager;
    }


    @Override
    public boolean launch(String[] command) throws IOException {
        if (command.length == 2)
        {
            try
            {
                var file_script = new File(command[1]);
                if (!file_script.canRead()) throw new NotEnoughRightsReadException();
                Scanner userScanner = new Scanner(file_script);

                scriptStack.add(command[1].trim());

                if (!userScanner.hasNext()) throw new EmptyFileException();

                while (userScanner.hasNext())
                {
                    String stroka_iter = userScanner.nextLine();
                    if (stroka_iter.isEmpty() || stroka_iter.trim().isEmpty())
                    {
                        continue;
                    }
                    String[] script_command_line = stroka_iter.trim().split("\\s+");

                    if (script_command_line[0].equals("execute_script"))
                    {
                        if (scriptStack.contains(script_command_line[1]))
                        {
                            scriptStack.clear();
                            throw new ScriptRecursionException();
                        }
                        else
                        {
                            scriptStack.add(script_command_line[1]);
                        }
                    }

                    System.out.printf("~~~~~ executing command <%s> ~~~~~\n", stroka_iter.trim());

                    if (script_command_line[0].isEmpty())
                    {
                        continue;
                    }

                    ACommand ACom = commandManager.getCommand(script_command_line[0]);

                    if (ACom != null)
                    {
                        ACom.launch(script_command_line);
                    }
                    else
                    {
                        System.out.println("-----=[ <" + script_command_line[0] + "> not found use <help> ]=-----");
                    }
                }

                scriptStack.remove(command[1]);

                System.out.printf("-----=[ <%s> script execution completed ]=-----\n" ,command[1]);

            }
            catch (ScriptRecursionException exception)
            {
                System.out.println("-----=[ script recursion error ]=-----");
            }
            catch (NotEnoughRightsReadException exception)
            {
                System.out.println("-----=[ not enough rights file error ]=-----");
            }
            catch (EmptyFileException exception)
            {
                System.out.println("-----=[ file empty error ]=-----");
            }
            catch (FileNotFoundException exception)
            {
                System.out.println("-----=[ file not found ]=-----");
            }
            catch (IllegalStateException exception)
            {
                System.out.println("-----=[ undefined error ]=-----");
            }
            finally
            {
                scriptStack.clear();
            }

            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }
}





