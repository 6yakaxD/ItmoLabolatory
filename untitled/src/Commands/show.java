package Commands;

import Collection.SpaceMarine;
import Tools.CollectionManager;

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
            if (collectionManager.get_main_collection().isEmpty())
            {
                System.out.println("-----=[ collection is empty ]=-----");
            }
            else
            {
                for (SpaceMarine spaceMarine : collectionManager.get_main_collection())
                {
                    System.out.println(spaceMarine.toString());
                }
            }
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }
    }


}
