package Commands;

import Tools.CollectionManager;
import Tools.Parser;


public class save extends ACommand
{
    CollectionManager collectionManager;
    Parser parser;
    public save(CollectionManager collectionManager, Parser parser)
    {
        super("save", "save current collection in json file");
        this.collectionManager = collectionManager;
        this.parser = parser;
    }

    @Override
    public boolean launch(String[] command)
    {
        if(command.length == 1)
        {
            parser.write_to_file("data.json", parser.get_json_from_arraylist(collectionManager.get_main_collection()));
            System.out.println("-----=[ successfully created json ]=-----");
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }

    }


}




















