package Commands;

import Tools.CollectionManager;



public class remove_lower extends ACommand
{
    private CollectionManager collectionManager;

    public remove_lower(CollectionManager collectionManager)
    {
        super("remove_lower [element_key]", "remove from a collection all elements greater than a given value");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            collectionManager.removeLowerThatElem(command[1]);
            System.out.println("Removed successfully");
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