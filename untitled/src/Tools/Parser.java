package Tools;

import Collection.SpaceMarine;
import CustomExeptions.LocalDateTimeAdapter;
import CustomExeptions.NotCorrectJsonData;
import CustomExeptions.NotEnoughRightsReadException;
import CustomExeptions.NotEnoughRightsWriteException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

import static Collection.SpaceMarine.validateAllValues;

public class Parser {


    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setDateFormat("dd/MM/yyyy")
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();



    public ArrayList<SpaceMarine> getStackFromJson(String json) {
        try {
            ArrayList<SpaceMarine> spacemarines = new ArrayList<>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<ArrayList<SpaceMarine>>() {
                }.getType();
                spacemarines = gson.fromJson(json, collectionType);

                for (SpaceMarine elem : spacemarines) {
                    if (!validateAllValues(elem)) throw new NotCorrectJsonData();
                }

               //spacemarines.sort((o1, o2) -> o1.compareTo(o2));

                return spacemarines;
            }
        }
        catch (Exception e)
        {
            System.out.println("-----=[ json file broken ]=-----");
        }
        catch (NotCorrectJsonData e)
        {
            System.out.println("-----=[ not correct json data ]=-----");
        }
        return new ArrayList<>();
    }

    public String getJsonFromStack(ArrayList<SpaceMarine> spaceMarine) {
        try {
            return gson.toJson(spaceMarine);
        } catch (Exception e) {
            System.out.println(e.toString());
            return "parsing error";
        }
    }




    public String readFromFile(String fileName) {
        var filePath = new File(fileName);
        InputStreamReader inputStreamReader;
        if(filePath.exists())
        {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
                StringBuilder stringFile = new StringBuilder();
                int symbolNow = inputStreamReader.read();
                while (symbolNow != -1) {
                    stringFile.append(((char) symbolNow));
                    symbolNow = inputStreamReader.read();
                }
                inputStreamReader.close();
                return stringFile.toString();
            } catch (IOException e) {
                System.out.println("-----=[ json file not found ]=-----");
                return "";
            }
        }

        return "";
    }


    public void writeToFile(String fileName, String text) throws IOException {
        File filePath = new File(fileName);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
        char[] chars = text.toCharArray();
        outputStreamWriter.write(chars, 0, chars.length);
        System.out.println("-----=[ collection was saved to json ]=-----");
        outputStreamWriter.close();
    }


}