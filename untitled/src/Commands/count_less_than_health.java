package Commands;

import Collection.SpaceMarine;
import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;


public class count_less_than_health extends ACommand
{
    private CollectionManager collectionManager;

    public count_less_than_health(CollectionManager collectionManager)
    {
        super("count_less_than_health [health_value]", "display count of elements whose health field value is lower than the specified one");
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

                for (int i = 0; i < collectionManager.get_main_collection().size(); i++) {
                    SpaceMarine a = collectionManager.get_main_collection().get(i);
                    if (a.getHealth() < healthInputValue) {
                        count++;
                    }
                }

                System.out.println("-----=[ " + count + " elements have health less than " + command[1] + " ]=-----");

            }
            catch (NumberFormatException  e)
            {
                System.out.println("-----=[ health_value must be a double ]=-----");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("-----=[ health_value must be not empty ]=-----");
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