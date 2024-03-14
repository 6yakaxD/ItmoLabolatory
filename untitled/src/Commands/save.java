package Commands;

import Tools.CollectionManager;
import Tools.Parser;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;


public class save extends ACommand
{
    CollectionManager collectionManager;
    Parser parser;
    String path;
    public save(CollectionManager collectionManager, Parser parser, String path)
    {
        super("save", "save current collection in json file");
        this.collectionManager = collectionManager;
        this.parser = parser;
        this.path = path;
    }


    @Override
    public boolean launch(String[] command) throws IOException {
        if(command.length == 1)
        {

            parser.writeToFile(path, parser.getJsonFromStack(collectionManager.get_main_collection()));
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }

    }


}




















