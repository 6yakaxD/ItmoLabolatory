package Commands;

import Collection.SpaceMarine;
import Commands.ACommand;
import Tools.CollectionManager;

public class remove_greater extends ACommand
{
    private CollectionManager collectionManager;

    public remove_greater(CollectionManager collectionManager)
    {
        super("remove_greater [element_key]", "remove from a collection all elements greater than a given value");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            collectionManager.removeGreateThatElem(command[1]);
            return true;
        }
        else
        {
            System.out.println("command <" + getName() + "> expect 1 argument");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }
    }


}