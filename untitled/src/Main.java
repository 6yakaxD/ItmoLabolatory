import Commands.*;
import Collection.SpaceMarine;
import Tools.CollectionManager;
import Tools.CommandManager;
import Tools.Console;
import Tools.Parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static Collection.SpaceMarine.validateAllValues;

public class Main
{
    public static void
    main
    (String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        CollectionManager clcmng = new CollectionManager();
        CommandManager cmdmng = new CommandManager()
        {{
            addCommand("help", new help(this));
            addCommand("exit", new exit());
            addCommand("execute_script",
                    new execute_script(this, scanner, clcmng));
            addCommand("insert",
                    new insert(scanner, clcmng));
            addCommand("show", new show(clcmng));
            addCommand("info", new info(clcmng));
            addCommand("clear", new clear(clcmng));
            addCommand("remove_key",
                    new remove_key(clcmng));
            addCommand("print_field_ascending_category",
                    new print_field_ascending_category(clcmng));
            addCommand("count_greater_than_health",
                    new count_greater_than_health(clcmng));
            addCommand("count_less_than_health",
                    new count_less_than_health(clcmng));
            addCommand("remove_greater",
                    new remove_greater(clcmng));
            addCommand("remove_lower",
                    new remove_lower(clcmng));
            addCommand("replace_if_lowe",
                    new replace_if_lowe(clcmng, scanner));
            addCommand("update", new update(clcmng, scanner));
            addCommand("save", new save(clcmng, parser));
            addCommand("get_from_json",
                    new get_from_json(clcmng, parser));
        }};
        Console console = new Console(scanner, cmdmng);

        try
        {
            String path;
            System.out.println("Enter the path to json from which you " +
                    "want to read the collection or press ENTER to skip");
            System.out.print("> ");
            path = scanner.nextLine();
            LinkedHashMap<String, SpaceMarine> marine =
                    parser.getLinckeHashMapFromJson(parser.readFromFile(path));
            if (marine != null) {
                int count_suck_validate = 0;
                for (Map.Entry<String, SpaceMarine> entry : marine.entrySet()) {
                    SpaceMarine spaceMarine = entry.getValue();
                    if(!validateAllValues(spaceMarine)){
                        count_suck_validate +=1;
                    }
                }
                if (count_suck_validate == 0){
                    clcmng.setSpaceMarineCollection(marine);
                }
                else {
                    System.out.println("Some data validated not correct in json");
                    System.out.println("Data was not insert in collection");
                }
            }
            console.interactiveModeInit();
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