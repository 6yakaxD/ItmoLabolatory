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
            parser.writeToFile("data.json", parser.getJsonFromLinkedHashMap(collectionManager.getSpaceMarineCollection()));
            System.out.println("Successfully created json");
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }

    }


}




















