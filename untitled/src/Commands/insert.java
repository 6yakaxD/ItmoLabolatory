package Commands;

import Collection.*;
import Commands.ACommand;
import Tools.CollectionManager;

import java.util.Scanner;
import Tools.GetCollectionElementInfoFromConsoleAsker;

public class insert extends ACommand
{
    private final Scanner scanner;
    private CollectionManager collectionManager;
    Coordinates coordinates = new Coordinates();
    Chapter chapter = new Chapter();
    GetCollectionElementInfoFromConsoleAsker asker = new GetCollectionElementInfoFromConsoleAsker();

    public insert(Scanner scanner, CollectionManager collectionManager)
    {
        super("insert [element_key]", "add a new element with the given key");
        this.scanner = scanner;
        this.collectionManager = collectionManager;
    }


    @Override
    public boolean launch(String[] command)
    {
        if(command.length == 2)
        {
            String keyToAddInCollectionManager = command[1];
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
            System.out.println("command <" + getName() + "> expect 1 argument and then all attributes of object");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }

    }


}
