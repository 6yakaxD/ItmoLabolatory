package Commands;

import Collection.SpaceMarine;
import Tools.CollectionManager;

import java.util.Collection;
import java.util.Map;

public class show extends ACommand
{
    private CollectionManager collectionManager;

    public show(CollectionManager collectionManager)
    {
        super("show", "print to standard output all the elements of the collection in string representation");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 1)
        {
            if (collectionManager.getSpaceMarineCollection().isEmpty())
            {
                System.out.println("Collection is empty");
            }
            else
            {
                for (Map.Entry<String, SpaceMarine> entry : collectionManager.getSpaceMarineCollection().entrySet())
                {
                    System.out.println(entry.getValue().toString());
                }
            }
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