import Collection.SpaceMarine;
import Commands.*;
import Tools.CollectionManager;
import Tools.CommandManager;
import Tools.Console;
import Tools.Parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static Collection.SpaceMarine.validateAllValues;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager()
        {{
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
            addCommand("save", new save(collectionManager, parser));
            addCommand("get_from_json", new get_from_json(collectionManager, parser));
        }};
        Console console = new Console(scanner, commandManager);

        String path;
        System.out.println("Enter the path to json from which you exit to read the collection or press ENTER to skip");
        System.out.print("> ");
        path = scanner.nextLine();
        LinkedHashMap<String, SpaceMarine> marine = parser.getLinckeHashMapFromJson(parser.readFromFile(path));
        if (marine != null)
        {
            int count_suck_validate = 0;
            for (Map.Entry<String, SpaceMarine> entry : marine.entrySet()) {
                SpaceMarine spaceMarine = entry.getValue();
                if(!validateAllValues(spaceMarine)){
                    count_suck_validate +=1;
                }
            }
            if (count_suck_validate == 0){
                collectionManager.setSpaceMarineCollection(marine);
            }
            else {
                System.out.println("Some data validated not correct in json");
                System.out.println("Data was not insert in collection");
            }
        }
        console.interactiveModeInit();
    }
}