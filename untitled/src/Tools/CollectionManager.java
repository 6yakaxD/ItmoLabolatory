package Tools;

import Collection.AstartesCategory;
import Collection.SpaceMarine;

import java.util.*;

public class CollectionManager
{
    LinkedHashMap<String, SpaceMarine> spaceMarines = new LinkedHashMap<>();

    public void setSpaceMarineCollection(LinkedHashMap<String, SpaceMarine> collection)
    {
        spaceMarines = collection;
    }

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
        SpaceMarine spaceMarine = getElementByKeyValue(key);
        if (spaceMarine == null)
        {
            System.out.println("No such element with key <" + key + ">");
        }
        else {
            spaceMarines.remove(key);
            System.out.println("Element with key <" + key +"> was deleted from collection");
        }

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
        if(getSpaceMarineCollection().isEmpty())
        {
            System.out.println("Collection is empty already");
        }
        else {
            System.out.println("Collection was cleared");
            spaceMarines.clear();
        }
    }

    public boolean isCollectionEmpty()
    {
        return spaceMarines.isEmpty();
    }

    public void printCategoryOfAllElements()
    {
        if (getSpaceMarineCollection().isEmpty())
        {
            System.out.println("Collection is empty");
        }
        else
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
    }

    public void removeGreateThatElem(String elem)
    {
        SpaceMarine spaceMarine = getElementByKeyValue(elem);
        if (spaceMarine == null)
        {
            System.out.println("No such element with key <" + elem + ">");
        }
        else {
            spaceMarines.values().removeIf(marine -> marine.getHealth() > spaceMarine.getHealth());
            System.out.println("Removed successfully");
        }
    }

    public void removeLowerThatElem(String elem_id)
    {
        SpaceMarine spaceMarine = getElementByKeyValue(elem_id);
        if (spaceMarine == null)
        {
            System.out.println("No such element with id <" + elem_id + ">");

        }
        else {
            spaceMarines.values().removeIf(marine -> marine.getHealth() < spaceMarine.getHealth());
            System.out.println("Removed successfully");
        }

    }

    public void replaceIfLowerByAttributeValue(String[] command)
    {
        String element_key = command[1];
        System.out.println();

        SpaceMarine spaceMarine = getElementByKeyValue(element_key);

    }

    public void UPDATE(int id_from_collection_to_update, SpaceMarine spaceMarine)
    {
        SpaceMarine a = getElementById(String.valueOf(id_from_collection_to_update));
        a.update(spaceMarine);
    }


}
