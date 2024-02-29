package Commands;

import Collection.Chapter;
import Collection.Coordinates;
import Collection.SpaceMarine;
import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;
import Tools.ElementInfo;

import java.util.Scanner;


public class update extends ACommand
{
    private final Scanner scanner;
    private CollectionManager collectionManager;
    private ElementInfo asker = new ElementInfo();
    private ElementInfo.AttributesToChange enumOfChangingAttributes;
    Coordinates coordinates = new Coordinates();
    Chapter chapter = new Chapter();

    public update(CollectionManager collectionManager, Scanner scanner)
    {
        super("update [element_id]", "update the attributes of a collection element whose id is equal to a given one");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }


    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            String id = command[1];
            int ID = 0;
            try
            {
                if (id.isEmpty()) throw new MustBeNotEmptyException();
                ID = Integer.parseInt(id);
            }
            catch (NumberFormatException  e)
            {
                System.out.println("Id must be an integer");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Id must be not empty");
            }

            SpaceMarine spaceMarine = collectionManager.getElementById(id);

            spaceMarine.setName(asker.askName());
            coordinates.setX(asker.askX());
            coordinates.setY(asker.askY());
            spaceMarine.setCoordinates(coordinates);
            spaceMarine.setCreationDate(asker.askCreationDate());
            spaceMarine.setHealth(asker.askHealth());
            spaceMarine.setAstartesCategory(asker.askAstartesCategory());
            spaceMarine.setWeaponType(asker.askWeapon());
            spaceMarine.setMeleeWeapon(asker.askMeleeWeapon());
            chapter.setName(asker.askChapterName());
            chapter.setWorld(asker.askChapterWorld());
            spaceMarine.setChapter(chapter);

            System.out.println(spaceMarine);

            System.out.println("Object <" + spaceMarine.getName() + ">  with Id <" + spaceMarine.getId() + "was successfully updated with all attributed");

            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }
    }


}