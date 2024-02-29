package Commands;

import Tools.CollectionManager;

public class remove_greater extends ACommand
{
    private CollectionManager collectionManager;

    public remove_greater(CollectionManager collectionManager)
    {
        super("remove_greater [element_name]", "remove from a collection all elements which health greater than a given value");
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
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }
    }


}