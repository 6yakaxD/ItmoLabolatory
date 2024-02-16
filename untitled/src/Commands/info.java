package Commands;

import Tools.CollectionManager;

public class info extends ACommand
{
    private CollectionManager collectionManager;

    public info(CollectionManager collectionManager)
    {
        super("info", "print information about the collection to standard output");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 1)
        {
            System.out.println(collectionManager.getInfoAboutCollection());
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