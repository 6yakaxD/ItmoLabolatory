import Collection.SpaceMarine;
import Commands.*;
import Tools.CollectionManager;
import Tools.CommandManager;
import Tools.Console;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager()
        {{
        CollectionManager collectionManager = new CollectionManager();
            addCommand("help", new help(this));
            addCommand("exit", new exit());
            addCommand("execute_script", new execute_script(this, scanner));
            addCommand("insert", new insert(scanner, collectionManager));
            addCommand("show", new show(collectionManager));
            addCommand("info", new info(collectionManager));
            addCommand("clear", new clear(collectionManager));
            addCommand("remove_key", new remove_key(collectionManager));
            addCommand("print_field_ascending_category", new print_field_ascending_category(collectionManager));
            addCommand("count_greater_than_health", new count_greater_than_health(collectionManager));
            addCommand("count_less_than_health", new count_less_than_health(collectionManager));
            addCommand("remove_greater", new remove_greater(collectionManager));
            addCommand("remove_lower", new remove_lower(collectionManager));
            addCommand("replace_if_lowe", new replace_if_lowe(collectionManager, scanner));
            addCommand("update", new update(collectionManager, scanner));
        }};
        Console console = new Console(scanner, commandManager);
        console.interactiveModeInit();
    }
}