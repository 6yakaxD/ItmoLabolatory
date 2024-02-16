package Commands;

import Collection.SpaceMarine;
import Tools.CollectionManager;

import javax.sound.midi.Soundbank;
import java.util.Scanner;


public class replace_if_lowe extends ACommand
{
    private CollectionManager collectionManager;
    private Scanner scanner;
    public replace_if_lowe(CollectionManager collectionManager, Scanner scanner)
    {
        super("replace_if_lowe [elem_key]", "replace a value by key if the new value is less than the old one");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            SpaceMarine spaceMarine =  collectionManager.getElementByKeyValue(command[1]);
            System.out.println("Input value: "); // если новое меньше старого -> заменить
            float new_value = scanner.nextFloat();
            if (new_value < spaceMarine.getHealth())
            {
                spaceMarine.setHealth(new_value);
            }
            else
            {
                System.out.println("New value not more than old");
            }
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