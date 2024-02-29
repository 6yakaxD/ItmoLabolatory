package Commands;

import Tools.CollectionManager;



public class remove_lower extends ACommand
{
    private CollectionManager collectionManager;

    public remove_lower(CollectionManager collectionManager)
    {
        super("remove_lower [element_name]", "remove from a collection all elements which health greater than a given value");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            collectionManager.removeLowerThatElem(command[1]);
            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }
    }


}