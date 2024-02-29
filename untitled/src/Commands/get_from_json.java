package Commands;

import Collection.SpaceMarine;
import Tools.CollectionManager;
import Tools.Parser;

import java.util.LinkedHashMap;
import java.util.Map;

import static Collection.SpaceMarine.validateAllValues;

public class get_from_json extends ACommand
{
    CollectionManager collectionManager;
    Parser parser;
    public get_from_json(CollectionManager collectionManager, Parser parser)
    {
        super("get_from_json [file_path]", "get data about collection from json");
        this.collectionManager = collectionManager;
        this.parser = parser;
    }

    @Override
    public boolean launch(String[] command)
    {
        if(command.length == 2)
        {
            String path = command[1];
            LinkedHashMap<String, SpaceMarine> marine = parser.getLinckeHashMapFromJson(parser.readFromFile(path));
            int count_suck_validate = 0;
            for (Map.Entry<String, SpaceMarine> entry : marine.entrySet()) {
                SpaceMarine spaceMarine = entry.getValue();
                if(!validateAllValues(spaceMarine)){
                    count_suck_validate +=1;
                }
            }
            if (count_suck_validate == 0){
                collectionManager.setSpaceMarineCollection(marine);
            }
            else {
                System.out.println("Some data validated not correct in json");
                System.out.println("Data was not insert in collection");
            }
            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }


}

