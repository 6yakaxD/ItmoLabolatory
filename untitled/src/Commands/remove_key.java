package Commands;

import Commands.ACommand;
import Tools.CollectionManager;


public class remove_key extends ACommand
{
    private CollectionManager collectionManager;

    public remove_key(CollectionManager collectionManager)
    {
        super("remove_key [element_key]", "remove an element from a collection by its key");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            collectionManager.removeElement(command[1]);
            return true;
        }
        else
        {
            System.out.println("command <" + getName() + "> expect 1 argument:");
            System.out.println(getName() + " [ key_value ]");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }

    }


}