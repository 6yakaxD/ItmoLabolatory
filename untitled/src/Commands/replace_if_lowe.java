package Commands;

import Collection.SpaceMarine;
import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;

import java.util.Scanner;


public class replace_if_lowe extends ACommand
{
    private CollectionManager collectionManager;
    private Scanner scanner;
    public replace_if_lowe(CollectionManager collectionManager, Scanner scanner)
    {
        super("replace_if_lowe [elem_name] [health_value]", "replace a value by name if the new health value is less than the old one");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 3)
        {
            SpaceMarine spaceMarine =  collectionManager.getElementByKeyValue(command[1]);
            if (spaceMarine == null)
            {
                System.out.println("No such element with key <" + command[1] + ">");
            }
            else {
                String health;
                double health_double = 0;
                try
                {
                    health = command[2];
                    if (health.isEmpty()) throw new MustBeNotEmptyException();
                    health_double = Integer.parseInt(health);
                    if (health_double < spaceMarine.getHealth())
                    {
                        spaceMarine.setHealth(health_double);
                        System.out.println("Done");
                    }
                    else
                    {
                        System.out.println("New value not more than old");
                    }
                }
                    catch (NumberFormatException  e)
                {
                    System.out.println("Health must be a double");
                }
                    catch (MustBeNotEmptyException e)
                {
                    System.out.println("Health must be not empty");
                }

            }
            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 2 argument ]=-----");
            return false;
        }
    }


}