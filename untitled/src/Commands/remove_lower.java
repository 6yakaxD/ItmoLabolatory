package Commands;

import Tools.CollectionManager;



public class remove_lower extends ACommand
{
    private CollectionManager collectionManager;

    public remove_lower(CollectionManager collectionManager)
    {
        super("remove_lower [health]", "remove from a collection all elements which health lower than a given value");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            try
            {
                double d = Double.parseDouble(command[1]);
                collectionManager.remove_elements_that_health_less_than(d);
                return true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("-----=[ health must be double ]=-----");
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