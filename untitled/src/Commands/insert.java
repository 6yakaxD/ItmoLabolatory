package Commands;

import Collection.*;
import Tools.CollectionManager;

import java.util.Scanner;
import Tools.ElementInfo;

public class insert extends ACommand
{
    private final Scanner scanner;
    private CollectionManager collectionManager;
    Coordinates coordinates = new Coordinates();
    Chapter chapter = new Chapter();
    ElementInfo asker = new ElementInfo();

    public insert(Scanner scanner, CollectionManager collectionManager)
    {
        super("insert", "add a new element");
        this.scanner = scanner;
        this.collectionManager = collectionManager;
    }


    @Override
    public boolean launch(String[] command)
    {
        if(command.length == 1)
        {
            SpaceMarine spaceMarine = new SpaceMarine();

            spaceMarine.setId(collectionManager.generateNewIdForElement());
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


            collectionManager.addElement(spaceMarine);
            System.out.println("Object <" + spaceMarine.getName() + "> was successfully added to the collection");
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }

    }


}
