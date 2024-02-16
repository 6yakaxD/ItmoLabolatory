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
            System.out.println("Collection was cleared");
            return true;
        }
        else
        {
            System.out.println("command <" + getName() + "> does not expect any arguments");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }
    }


}