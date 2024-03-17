import Commands.*;
import Collection.SpaceMarine;
import Tools.CollectionManager;
import Tools.CommandManager;
import Tools.Console;
import Tools.Parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static Collection.SpaceMarine.validateAllValues;

public class Main
{
    public static void main(String[] console_input) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        var fillee = new File(console_input[0]);
        if ((console_input.length == 1) && (fillee.exists()))
        {
            try
            {
                String path = console_input[0];

                Parser parser = new Parser();
                CollectionManager collection_manager = new CollectionManager();
                CommandManager command_manager = new CommandManager()
                {{
                    addCommand("help", new help(this));
                    addCommand("exit", new exit());
                    addCommand("execute_script", new execute_script(this, scanner, collection_manager));
                    addCommand("insert", new insert(scanner, collection_manager));
                    addCommand("show", new show(collection_manager));
                    addCommand("info", new info(collection_manager));
                    addCommand("clear", new clear(collection_manager));
                    addCommand("remove_id", new remove_id(collection_manager));
                    addCommand("print_field_ascending_category", new print_field_ascending_category(collection_manager));
                    addCommand("count_greater_than_health", new count_greater_than_health(collection_manager));
                    addCommand("count_less_than_health", new count_less_than_health(collection_manager));
                    addCommand("remove_greater", new remove_greater(collection_manager));
                    addCommand("remove_lower", new remove_lower(collection_manager));
                    addCommand("replace_if_lowe", new replace_if_lowe(collection_manager, scanner));
                    addCommand("update", new update(collection_manager, scanner));
                    addCommand("save", new save(collection_manager, parser, path));
                    addCommand("get_from_json", new get_from_json(collection_manager, parser));
                }};
                Console console = new Console(scanner, command_manager);


                ArrayList<SpaceMarine> marine = parser.getStackFromJson(parser.readFromFile(path));
                if (marine != null) {
                    int count_suck_validate = 0;
                    for (SpaceMarine spaceMarine : marine) {
                        if(!validateAllValues(spaceMarine)){
                            count_suck_validate +=1;
                        }
                    }
                    if (count_suck_validate == 0){
                        collection_manager.set_main_collection(marine);
                    }
                    else {
                        System.out.println("-----=[ data from json validated not correct ]=-----");
                    }
                }
                console.interactiveModeInit();
            }
            catch (NoSuchElementException e)
            {
                System.out.println("-----=[ end of input ]=-----");
            }
            finally
            {
                scanner.close();
            }

        }
        else
        {
            System.out.println("----=[ you must insert path to json in command line ]=-----");
        }

    }


}