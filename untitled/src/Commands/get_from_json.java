package Commands;

import Collection.SpaceMarine;
import Tools.CollectionManager;
import Tools.Parser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
    public boolean launch(String[] command) throws IOException {
        if(command.length == 2)
        {
            ArrayList<SpaceMarine> marine = parser.getStackFromJson(parser.readFromFile(command[1]));
            if (marine != null) {
                int count_suck_validate = 0;
                for (SpaceMarine spaceMarine : marine) {
                    if(!validateAllValues(spaceMarine)){
                        count_suck_validate +=1;
                    }
                }
                if (count_suck_validate == 0){
                    collectionManager.set_main_collection(marine);
                    System.out.println("-----=[ data was correctly added ]=-----");
                }
                else {
                    System.out.println("-----=[ data from json validated not correct ]=-----");
                }
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

