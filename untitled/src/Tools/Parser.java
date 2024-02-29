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



    /**
     * Получает стек групп из json-строки
     *
     * @param json начальная json-строка
     * @return стек (коллекция) групп
     */
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
            System.out.println("Json-файл повреждён, данные из него не были взяты. Коллекция, с которой вы работаете пуста");
        }
        catch (NotCorrectJsonData e)
        {
            System.out.println("Not correct json data");
        }
        return new ArrayList<>();
    }


    /**
     * Получает json-строку из связанного списка работников
     *
     * @param spaceMarine стек (коллекция) групп
     * @return json-строка
     */
    public String getJsonFromStack(ArrayList<SpaceMarine> spaceMarine) {
        try {
            return gson.toJson(spaceMarine);
        } catch (Exception e) {
            System.out.println(e.toString());
            return "ошибка парсинга";
        }
    }




    /**
     * Чтение текста из файла
     *
     * @param fileName имя файла
     * @return String текст из файла
     */
    public String readFromFile(String fileName) {
        var filePath = new File(fileName);
        InputStreamReader inputStreamReader;
        try {

            if (!filePath.canRead()) throw new NotEnoughRightsReadException();
            if (!filePath.canWrite()) System.out.println("Внимание! Вы не сможете использовать команду save!");

            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
            StringBuilder stringFile = new StringBuilder();
            int symbolNow = inputStreamReader.read();
            while (symbolNow != -1) {
                stringFile.append(((char) symbolNow));
                symbolNow = inputStreamReader.read();
            }
            inputStreamReader.close();
            return stringFile.toString();
        } catch (NotEnoughRightsReadException e) {
            System.out.println(e + " Коллекция пуста!");
            return "";
        } catch (IOException e) {
            System.out.println("Json-файл не найден. Коллекция пуста!");
            return "";
        }
    }

    /**
     * Запись текста в файл
     *
     * @param fileName имя файла
     * @param text     текст для файла
     */
    public void writeToFile(String fileName, String text) {
        try {
            var filePath = new File(fileName);
            if (!filePath.canWrite()) throw new NotEnoughRightsWriteException();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
            char[] chars = text.toCharArray();
            outputStreamWriter.write(chars, 0, chars.length);
            System.out.println("Коллекция была успешно сохранена");
            outputStreamWriter.close();
        } catch (NotEnoughRightsWriteException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println("ошибка при записи файла!");
        }
    }


}