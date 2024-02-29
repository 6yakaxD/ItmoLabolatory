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
            SpaceMarine spaceMarine = collectionManager.get_element_by_name(command[1]);
            if (spaceMarine == null)
            {
                System.out.println("-----=[ no such element with name <" + command[1] + "> ]=-----");
            }
            else {
                String health;
                double health_double = 0;
                try
                {
                    health = command[2];
                    if (health.isEmpty()) throw new MustBeNotEmptyException();
                    health_double = Double.parseDouble(health);
                    if (health_double < spaceMarine.getHealth())
                    {
                        spaceMarine.setHealth(health_double);
                        System.out.printf("-----=[ new value set in element <%s> ]=-----\n", command[1]);
                    }
                    else
                    {
                        System.out.println("-----=[ new value not more than old ]=-----");
                    }
                }
                    catch (NumberFormatException  e)
                {
                    System.out.println("-----=[ health must be a double ]=-----");
                }
                    catch (MustBeNotEmptyException e)
                {
                    System.out.println("-----=[ health must be not empty ]=-----");
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