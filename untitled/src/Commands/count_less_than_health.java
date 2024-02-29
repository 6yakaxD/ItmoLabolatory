package Commands;

import Collection.SpaceMarine;
import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;


public class count_less_than_health extends ACommand
{
    private CollectionManager collectionManager;

    public count_less_than_health(CollectionManager collectionManager)
    {
        super("count_less_than_health [health_value]", "display the number of elements whose health field value is lower than the specified one");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            double healthInputValue = 0;
            try
            {
                if (command[1].isEmpty()) throw new MustBeNotEmptyException();
                healthInputValue = Double.parseDouble(command[1]);
                int count = 0;
                for (SpaceMarine marine : collectionManager.getSpaceMarineCollection().values()) {
                    if (marine.getHealth() < healthInputValue) {
                        count++;
                    }
                }

                System.out.println("Count of elements in collection with health lower than <" + command[1] + "> -> " + count);
            }
            catch (NumberFormatException  e)
            {
                System.out.println("health_value must be a double");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("health_value must be not empty");
            }

            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }
    }


}