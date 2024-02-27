package Tools;


import Collection.SpaceMarine;
import CustomExeptions.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
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
    public LinkedHashMap<String, SpaceMarine> getLinckeHashMapFromJson(String json)
    {
        try {
            LinkedHashMap<String, SpaceMarine> spaceMarines = new LinkedHashMap<String, SpaceMarine>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<LinkedHashMap<String, SpaceMarine>>() {
                }.getType();
                spaceMarines = gson.fromJson(json, collectionType);

            }
            return spaceMarines;
        } catch (Exception e) {
            return new LinkedHashMap<String, SpaceMarine>();
        }
    }

    /**
     * Получает json-строку из связанного списка работников
     */
    public String getJsonFromLinkedHashMap(LinkedHashMap<String, SpaceMarine> studyGroups)
    {
        try {
            String json = gson.toJson(studyGroups);
            return json;
        } catch (Exception e) {
            System.out.println(e.toString());
            return "parse error";
        }
    }


    /**
     * Чтение текста из файла
     *
     * @param fileName имя файла
     * @return String текст из файла
     */
    public String readFromFile(String fileName)
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
            System.out.println("Json not found");
            return "error read json";
        }
    }

    /**
     * Запись текста в файл
     *
     * @param fileName имя файла
     * @param text текст для файла
     */
    public void writeToFile(String fileName, String text)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
            char[] chars = text.toCharArray();
            outputStreamWriter.write(chars, 0, chars.length);
            outputStreamWriter.close();
        } catch (IOException e) {
            System.out.println("error to write file");
        }
    }

    /**
     * Метод для получения имени файла из переменной окружения
     *
     * @return имя файла
     */
    public static String getName()
    {
        path = System.getenv("appdata");
        return path;
    }


}
