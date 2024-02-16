package Commands;

import Collection.SpaceMarine;
import Tools.CollectionManager;


public class count_less_than_health extends ACommand
{
    private CollectionManager collectionManager;

    public count_less_than_health(CollectionManager collectionManager)
    {
        super("count_greater_than_health [health_value]", "display the number of elements whose health field value is lower than the specified one");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            double healthInputValue = Double.parseDouble(command[1]);
            int count = 0;
            for (SpaceMarine marine : collectionManager.getSpaceMarineCollection().values()) {
                if (marine.getHealth() < healthInputValue) {
                    count++;
                }
            }

            System.out.println("Count of elements in collection with health more than <" + command[1] + "> -> < " + count + ">");
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