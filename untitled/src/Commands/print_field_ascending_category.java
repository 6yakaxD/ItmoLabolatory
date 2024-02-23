package Commands;

import Commands.ACommand;
import Tools.CollectionManager;


public class print_field_ascending_category extends ACommand
{
    private CollectionManager collectionManager;

    public print_field_ascending_category(CollectionManager collectionManager)
    {
        super("print_field_ascending_category", "display the category field values of all elements in ascending order");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 1)
        {
            collectionManager.printCategoryOfAllElements();
            System.out.println();
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