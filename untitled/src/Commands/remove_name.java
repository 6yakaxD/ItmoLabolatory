package Commands;

import Tools.CollectionManager;


public class remove_name extends ACommand
{
    private CollectionManager collectionManager;

    public remove_name(CollectionManager collectionManager)
    {
        super("remove_name [element_name]", "remove an element from a collection by its name");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            collectionManager.remove_element_by_name(command[1]);
            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }


}