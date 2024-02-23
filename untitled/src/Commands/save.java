package Commands;

import Tools.CollectionManager;
import Tools.Parser;


public class save extends ACommand
{
    CollectionManager collectionManager;
    Parser parser;
    public save(CollectionManager collectionManager, Parser parser)
    {
        super("save", "save current collection in xml file");
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
            System.out.println("command <" + getName() + "> does not expect any arguments");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }

    }


}




















