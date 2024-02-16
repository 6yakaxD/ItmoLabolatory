package Commands;

import Collection.Chapter;
import Collection.Coordinates;
import Collection.SpaceMarine;
import Tools.CollectionManager;
import Tools.GetCollectionElementInfoFromConsoleAsker;

import java.util.Scanner;


public class update extends ACommand
{
    private final Scanner scanner;
    private CollectionManager collectionManager;
    private GetCollectionElementInfoFromConsoleAsker asker = new GetCollectionElementInfoFromConsoleAsker();
    private GetCollectionElementInfoFromConsoleAsker.AttributesToChange enumOfChangingAttributes;

    public update(CollectionManager collectionManager, Scanner scanner)
    {
        super("update [element_id]", "update the value of a collection element whose id is equal to a given one");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }


    @Override
    public boolean launch(String[] command) {
        if(command.length == 2)
        {
            String elem_id = command[1];
            SpaceMarine spaceMarine = collectionManager.getElementById(elem_id);
            enumOfChangingAttributes = asker.askAttributesToChange(); // по какому атрибуту

            switch (enumOfChangingAttributes.toString())
            {
                case "NAME":
                {
                    spaceMarine.setName(asker.askName());
                }
                case "COORDINATES":
                {
                    Coordinates coordinates = null;
                    int x = asker.askX();
                    float y = asker.askY();
                    coordinates.setX(x);
                    coordinates.setY(y);
                    spaceMarine.setCoordinates(coordinates);
                }
                case "HEALTH":
                {
                    spaceMarine.setHealth(asker.askHealth());
                }
                case "WEAPONTYPE":
                {
                    spaceMarine.setWeaponType(asker.askWeapon());
                }
                case "MELEEWEAPON":
                {
                    spaceMarine.setMeleeWeapon(asker.askMeleeWeapon());
                }
                case "CHAPTER":
                {
                    Chapter chapter = null;
                    String name = asker.askChapterName();
                    String world = asker.askChapterWorld();
                    chapter.setWorld(world);
                    chapter.setName(name);
                    spaceMarine.setChapter(chapter);
                }
            }
            System.out.println("Done Successfully");

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