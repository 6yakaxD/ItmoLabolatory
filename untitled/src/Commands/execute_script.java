package Commands;


import Collection.*;
import CustomExeptions.MustBeLessThan617;
import CustomExeptions.MustBeMoreThanZero;
import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;
import Tools.CommandManager;
import Tools.ElementInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class execute_script extends ACommand
{
    CommandManager commandManager;
    Scanner scanner;
    CollectionManager collectionManager;
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
            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }
}





