package Tools;

import Collection.AstartesCategory;
import Collection.SpaceMarine;

import java.util.*;

public class CollectionManager
{

    // сделать ArrayList
    ArrayList<SpaceMarine> main_collection = new ArrayList<SpaceMarine>();

    public void set_main_collection(ArrayList<SpaceMarine> collection)
    {
        this.main_collection = collection;
    }

    public ArrayList<SpaceMarine> get_main_collection()
    {
        return main_collection;
    }

    public String get_info_about_main_collection(){
        return "type - " + main_collection.getClass() + "\n" +
                "count - " + main_collection.size();
    }

    public void clear_main_collection()
    {
        if(get_main_collection().isEmpty())
        {
            System.out.println("-----=[ collection is empty already ]=-----");
        }
        else {
            main_collection.clear();
            System.out.println("-----=[ collection was cleared ]=-----");
        }
    }

    public void print_category_of_all_elements()
    {
        if (get_main_collection().isEmpty())
        {
            System.out.println("Collection is empty");
        }
        else
        {
            List<AstartesCategory> categories = new ArrayList<>();
            for (SpaceMarine marine : main_collection) {
                categories.add(marine.getAstartesCategory());
            }

            categories.sort(Comparator.naturalOrder());

            for (AstartesCategory category : categories) {
                System.out.println(category.name());
            }
        }
    }

    public void remove_elements_that_health_more_than(double health)
    {
        main_collection.removeIf(marine -> marine.getHealth() > health);
    }

    public void remove_elements_that_health_less_than(double health)
    {
        main_collection.removeIf(marine -> marine.getHealth() < health);
    }

    public SpaceMarine get_element_by_name(String name) {
        for (SpaceMarine marine : main_collection) {
            if (marine.getName().equals(name)) {
                return marine;
            }
        }
        return null;
    }

    public void remove_element_by_name(String name)
    {
        if (name != null)
        {
            main_collection.remove(get_element_by_name(name));
        }
        else {
            System.out.println("-----=[ no suck element " + name + " ]=-----");
        }

    }

    public void addElement(SpaceMarine marine)
    {
        main_collection.add(marine);
    }

    public long generate_new_id_for_element()
    {
        return main_collection.size() + 1;
    }


    public SpaceMarine get_element_by_id(long id)
    {
        for (SpaceMarine spaceMarine : main_collection)
        {
            if (spaceMarine.getId() == id)
            {
                return spaceMarine;
            }
        }
        return null;
    }


}
