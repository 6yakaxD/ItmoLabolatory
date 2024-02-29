package Commands;

import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;
import Tools.ElementInfo;

import java.util.NoSuchElementException;


public class remove_id extends ACommand
{
    private CollectionManager collectionManager;

    public remove_id(CollectionManager collectionManager)
    {
        super("remove_id [element_id]", "remove an element from a collection by its id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            try
            {
                long ID = Long.parseLong(command[1]);
                collectionManager.remove_element_by_id(ID);
                return true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("-----=[ id must be long ]=-----");
                return false;
            }
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }


}