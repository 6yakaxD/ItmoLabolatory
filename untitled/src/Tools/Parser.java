package Tools;


import Collection.SpaceMarine;
import CustomExeptions.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Parser
{
    public static String path;

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setDateFormat("dd/MM/yyyy")
            .registerTypeAdapter(LocalDateTime.class,
                    new LocalDateTimeAdapter())
            .create();


    /**
     * Получает стек групп из json-строки
     */
    public ArrayList<SpaceMarine> get_arraylist_from_json(String json)
    {
        try {
            ArrayList<SpaceMarine> spaceMarines = new ArrayList<SpaceMarine>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<ArrayList<SpaceMarine>>() {
                }.getType();
                spaceMarines = gson.fromJson(json, collectionType);

            }
            return spaceMarines;
        } catch (Exception e) {
            return new ArrayList<SpaceMarine>();
        }
    }

    /**
     * Получает json-строку из связанного списка работников
     */
    public String get_json_from_arraylist(ArrayList<SpaceMarine> spaceMarine)
    {
        try {
            String json = gson.toJson(spaceMarine);
            return json;
        } catch (Exception e) {
            System.out.println(e.toString());
            return "-----=[ error parsing ]=-----";
        }
    }


    /**
     * Чтение текста из файла
     *
     * @param fileName имя файла
     * @return String текст из файла
     */
    public String read_from_file(String fileName)
    {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
            String stringFile = "";
            int symbolNow = inputStreamReader.read();
            while (symbolNow != -1) {
                stringFile += String.valueOf((char) symbolNow);
                symbolNow = inputStreamReader.read();
            }
            inputStreamReader.close();
            return stringFile;
        }
        catch (IOException e) {
            System.out.println("-----=[ error json not found ]=-----");
            return "-----=[ error json reading ]=-----";
        }
    }

    /**
     * Запись текста в файл
     *
     * @param fileName имя файла
     * @param text текст для файла
     */
    public void write_to_file(String fileName, String text)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
            char[] chars = text.toCharArray();
            outputStreamWriter.write(chars, 0, chars.length);
            outputStreamWriter.close();
        } catch (IOException e) {
            System.out.println("-----=[ error file writing ]=-----");
        }
    }



}
