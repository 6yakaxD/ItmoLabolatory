package Commands;

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
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }
    }


}