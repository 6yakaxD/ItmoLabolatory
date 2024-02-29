package Commands;

import Tools.CollectionManager;


public class remove_key extends ACommand
{
    private CollectionManager collectionManager;

    public remove_key(CollectionManager collectionManager)
    {
        super("remove_key [element_name]", "remove an element from a collection by its name");
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
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }


}