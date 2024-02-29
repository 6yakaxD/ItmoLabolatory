package Commands;

import Tools.CollectionManager;

public class clear extends ACommand
{
    private CollectionManager collectionManager;

    public clear(CollectionManager collectionManager)
    {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 1)
        {
            collectionManager.clearFullCollection();
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }
    }


}