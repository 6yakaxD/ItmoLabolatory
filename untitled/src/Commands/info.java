package Commands;

import Tools.CollectionManager;

public class info extends ACommand
{
    private CollectionManager collectionManager;

    public info(CollectionManager collectionManager)
    {
        super("info", "print information about the collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 1)
        {
            System.out.println(collectionManager.get_info_about_main_collection());
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }

    }


}