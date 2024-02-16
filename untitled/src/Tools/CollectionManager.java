package Tools;

import Collection.AstartesCategory;
import Collection.SpaceMarine;

import java.util.*;

public class CollectionManager
{
    LinkedHashMap<String, SpaceMarine> spaceMarines = new LinkedHashMap<>();

    public SpaceMarine getElementById(String id)
    {
        long Id = Long.parseLong(id);
        SpaceMarine marineToFind = null;
        for (SpaceMarine marine : spaceMarines.values()) {
            if (marine.getId() == Id)
            {
                marineToFind = marine;
            }
        }
        if (marineToFind == null)
        {
            System.out.println("No element found with ID: " + Id);
        }
        return marineToFind;
    }

    public void addElement(SpaceMarine marine)
    {
        spaceMarines.put(marine.getName(), marine);
    }

    public void removeElement(String key)
    {
        spaceMarines.remove(key);
    }

    public SpaceMarine getElementByKeyValue(String name)
    {
        return spaceMarines.get(name);
    }

    public LinkedHashMap<String, SpaceMarine> getSpaceMarineCollection()
    {
        return spaceMarines;
    }

    public String getInfoAboutCollection(){
        return "Type - " + spaceMarines.getClass() + "\n" +
                "Amount of elements - " + spaceMarines.size();
    }

    public long generateNewIdForElement()
    {
        return spaceMarines.size() + 1;
    }

    public void clearFullCollection()
    {
        spaceMarines.clear();
    }

    public boolean isCollectionEmpty()
    {
        return spaceMarines.isEmpty();
    }

    public void printCategoryOfAllElements()
    {
        List<AstartesCategory> categories = new ArrayList<>();
        for (SpaceMarine marine : spaceMarines.values()) {
            categories.add(marine.getAstartesCategory());
        }

        categories.sort(Comparator.naturalOrder());

        for (AstartesCategory category : categories) {
            System.out.println(category.name());
        }
    }

    public void removeGreateThatElem(String elem)
    {
        SpaceMarine spaceMarine = getElementByKeyValue(elem);
        spaceMarines.values().removeIf(marine -> marine.getHealth() > spaceMarine.getHealth());
    }

    public void removeLowerThatElem(String elem_id)
    {
        SpaceMarine spaceMarine = getElementByKeyValue(elem_id);
        spaceMarines.values().removeIf(marine -> marine.getHealth() < spaceMarine.getHealth());
    }

    public void replaceIfLowerByAttributeValue(String[] command)
    {
        String element_key = command[1];
        System.out.println();

        SpaceMarine spaceMarine = getElementByKeyValue(element_key);

    }
}
