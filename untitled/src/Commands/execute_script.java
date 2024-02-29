package Commands;


import Collection.*;
import CustomExeptions.MustBeLessThan617;
import CustomExeptions.MustBeMoreThanZero;
import CustomExeptions.MustBeNotEmptyException;
import Tools.CollectionManager;
import Tools.CommandManager;
import Tools.ElementInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class execute_script extends ACommand
{
    CommandManager commandManager;
    Scanner scanner;
    CollectionManager collectionManager;
    public execute_script(CommandManager commandManager, Scanner scanner, CollectionManager collectionManager)
    {
        super("execute_script [file_path]", "read and execute the script from the specified file");
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean launch(String[] command) throws IOException {
        if (command.length == 2) {

            String filePath = command[1];
            File file = new File(filePath);

            Set<String> processedFiles = new HashSet<>();
            processedFiles.add(filePath);


            if (!file.exists()) {
                System.out.println("File <" + filePath + "> doesnt exists: ");
                return false;
            }
            if (!file.isFile()) {
                System.out.println("Cannot open File <" + filePath + ">");
                return false;
            }
            if (!file.canRead()) {
                System.out.println("Cannot read File <" + filePath + ">");
                return false;
            }
            if (file.length() == 0) {
                System.out.println("File <" + filePath + "> is empty");
                return false;
            }

            System.out.println("-----=[ Starting script execution ]=-----");

            List<String> validCommands = new ArrayList<>();
            ACommand script_command;

            List<String> lines;         // строки в скрипте
            lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                /* skip если строка в скрипте пустая */
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] total_script_command = line.split("\\s+");
                script_command = commandManager.getCommand(total_script_command[0]);
                if (script_command == null) {
                    System.out.println("Seems command <" + total_script_command[0] + "> from script not exists");
                    return false;
                }
                if (total_script_command[0].equals("insert")) {
                    if(total_script_command.length == 11){
                        Coordinates coordinates = new Coordinates();
                        Chapter chapter = new Chapter();
                        ElementInfo asker = new ElementInfo();
                        String keyToAddInCollectionManager = total_script_command[1];
                        SpaceMarine spaceMarine = new SpaceMarine();

                        spaceMarine.setId(collectionManager.generateNewIdForElement());


                        String name = total_script_command[2];
                        if (name.isEmpty()) {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Name is Empty (3 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        spaceMarine.setName(name);


                        int x;
                        try
                        {
                            if (total_script_command[3].isEmpty()) throw new MustBeNotEmptyException();
                            x = Integer.parseInt(total_script_command[3]);
                        }
                        catch (NumberFormatException  e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("X must be an integer (4 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("X must be not empty (4 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        coordinates.setX(x);


                        float y = 0;
                        try
                        {
                            if (total_script_command[4].isEmpty()) throw new MustBeNotEmptyException();
                            y = Float.parseFloat(total_script_command[4]);
                            if (y > 617) throw new MustBeLessThan617();
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Y must be a float (5 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Y must be not empty (5 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeLessThan617 e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Y must be less 617 (5 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        coordinates.setY(y);
                        spaceMarine.setCoordinates(coordinates);



                        spaceMarine.setCreationDate(asker.askCreationDate());


                        double health;
                        try
                        {
                            if (total_script_command[5].isEmpty()) throw new MustBeNotEmptyException();
                            health = Double.parseDouble(total_script_command[5]);
                            if (health <= 0) throw new MustBeMoreThanZero();
                        }
                        catch (MustBeMoreThanZero e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Health value must be more than 0 (6 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Health must be a float (6 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Health must be not empty (6 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        spaceMarine.setHealth(health);


                        AstartesCategory astartesCategory;
                        try
                        {
                            if(total_script_command[6].isEmpty()) throw new MustBeNotEmptyException();
                            astartesCategory = AstartesCategory.valueOf(total_script_command[6].toUpperCase());
                        }
                        catch (NoSuchElementException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Type can't be recognized (7 argument)");
                            System.out.println("Categories: " + AstartesCategory.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (IllegalArgumentException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("There is no such value in the category (7 argument)");
                            System.out.println("Categories: " + AstartesCategory.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Astartes Category must be not empty (7 argument)");
                            System.out.println("Categories: " + AstartesCategory.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        spaceMarine.setAstartesCategory(astartesCategory);


                        Weapon weapon;
                        try
                        {
                            if(total_script_command[7].isEmpty()) throw new MustBeNotEmptyException();
                            weapon = Weapon.valueOf(total_script_command[7].toUpperCase());
                        }
                        catch (NoSuchElementException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Type can't be recognized (8 argument)");
                            System.out.println("Types: " + Weapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (IllegalArgumentException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("There is no such value in the category (8 argument)");
                            System.out.println("Types: " + Weapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Weapon Type must be not empty (8 argument)");
                            System.out.println("Types: " + Weapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        spaceMarine.setWeaponType(weapon);


                        MeleeWeapon meleeWeapon;
                        try
                        {
                            if(total_script_command[8].isEmpty()) throw new MustBeNotEmptyException();;
                            meleeWeapon = MeleeWeapon.valueOf(total_script_command[8].toUpperCase());
                        }
                        catch (NoSuchElementException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Type can't be recognized (9 argument)");
                            System.out.println("Types: " + MeleeWeapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (IllegalArgumentException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("There is no such value in the category (9 argument)");
                            System.out.println("Types: " + MeleeWeapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Melee Weapon must be not empty (9 argument)");
                            System.out.println("Types: " + MeleeWeapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        spaceMarine.setMeleeWeapon(meleeWeapon);


                        String chapterName;
                        try
                        {
                            chapterName = total_script_command[9];
                            if (chapterName.isEmpty()) throw new MustBeNotEmptyException();
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Chapter Name must be not empty (10 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        chapter.setName(chapterName);

                        String chapterWorld = null;
                        try
                        {
                            chapterWorld = total_script_command[10];
                            if (chapterWorld.isEmpty()) throw new MustBeNotEmptyException();
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Chapter world must be not empty (11 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        chapter.setWorld(chapterWorld);
                        spaceMarine.setChapter(chapter);

                        collectionManager.addElement(spaceMarine);

                        return true;

                    }
                    else {
                        System.out.println("command <insert> in script expect 11 arguments of object's attributes");
                        System.out.println("EXAMPLE:\ninsert [key] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                        for (int i=1; i<total_script_command.length; i++)
                        {
                            System.out.println("unused argument -> " + total_script_command[i]);
                        }
                        return false;
                    }

                }
                if (total_script_command[0].equals("update")) {
                    if (total_script_command.length == 11){
                        Coordinates coordinates = new Coordinates();
                        Chapter chapter = new Chapter();
                        ElementInfo asker = new ElementInfo();

                        SpaceMarine space_marine_local = new SpaceMarine();

                        String id = total_script_command[1];
                        int ID = 0;
                        try
                        {
                            if (id.isEmpty()) throw new MustBeNotEmptyException();
                            ID = Integer.parseInt(id);
                        }
                        catch (NumberFormatException  e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Id must be an integer (2 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Id must be not empty (2 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }

                        SpaceMarine space_marine_orig_by_id = collectionManager.getElementById(id);



                        String name = total_script_command[2];
                        if (name.isEmpty()) {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Name is Empty (3 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        space_marine_local.setName(name);


                        int x;
                        try
                        {
                            if (total_script_command[3].isEmpty()) throw new MustBeNotEmptyException();
                            x = Integer.parseInt(total_script_command[3]);
                        }
                        catch (NumberFormatException  e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("X must be an integer (4 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("X must be not empty (4 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        coordinates.setX(x);


                        float y = 0;
                        try
                        {
                            if (total_script_command[4].isEmpty()) throw new MustBeNotEmptyException();
                            y = Float.parseFloat(total_script_command[4]);
                            if (y > 617) throw new MustBeLessThan617();
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Y must be a float (5 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Y must be not empty (5 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeLessThan617 e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Y must be less 617 (5 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        coordinates.setY(y);
                        space_marine_local.setCoordinates(coordinates);



                        space_marine_local.setCreationDate(asker.askCreationDate());


                        double health;
                        try
                        {
                            if (total_script_command[5].isEmpty()) throw new MustBeNotEmptyException();
                            health = Double.parseDouble(total_script_command[5]);
                            if (health <= 0) throw new MustBeMoreThanZero();
                        }
                        catch (MustBeMoreThanZero e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Health value must be more than 0 (6 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Health must be a float (6 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Health must be not empty (6 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        space_marine_local.setHealth(health);


                        AstartesCategory astartesCategory;
                        try
                        {
                            if(total_script_command[6].isEmpty()) throw new MustBeNotEmptyException();
                            astartesCategory = AstartesCategory.valueOf(total_script_command[6].toUpperCase());
                        }
                        catch (NoSuchElementException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Type can't be recognized (7 argument)");
                            System.out.println("Categories: " + AstartesCategory.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (IllegalArgumentException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("There is no such value in the category (7 argument)");
                            System.out.println("Categories: " + AstartesCategory.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Astartes Category must be not empty (7 argument)");
                            System.out.println("Categories: " + AstartesCategory.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        space_marine_local.setAstartesCategory(astartesCategory);


                        Weapon weapon;
                        try
                        {
                            if(total_script_command[7].isEmpty()) throw new MustBeNotEmptyException();
                            weapon = Weapon.valueOf(total_script_command[7].toUpperCase());
                        }
                        catch (NoSuchElementException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Type can't be recognized (8 argument)");
                            System.out.println("Types: " + Weapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (IllegalArgumentException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("There is no such value in the category (8 argument)");
                            System.out.println("Types: " + Weapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Weapon Type must be not empty (8 argument)");
                            System.out.println("Types: " + Weapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        space_marine_local.setWeaponType(weapon);


                        MeleeWeapon meleeWeapon;
                        try
                        {
                            if(total_script_command[8].isEmpty()) throw new MustBeNotEmptyException();;
                            meleeWeapon = MeleeWeapon.valueOf(total_script_command[8].toUpperCase());
                        }
                        catch (NoSuchElementException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Type can't be recognized (9 argument)");
                            System.out.println("Types: " + MeleeWeapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (IllegalArgumentException exception)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("There is no such value in the category (9 argument)");
                            System.out.println("Types: " + MeleeWeapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Melee Weapon must be not empty (9 argument)");
                            System.out.println("Types: " + MeleeWeapon.nameList());
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        space_marine_local.setMeleeWeapon(meleeWeapon);


                        String chapterName;
                        try
                        {
                            chapterName = total_script_command[9];
                            if (chapterName.isEmpty()) throw new MustBeNotEmptyException();
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Chapter Name must be not empty (10 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        chapter.setName(chapterName);

                        String chapterWorld = null;
                        try
                        {
                            chapterWorld = total_script_command[10];
                            if (chapterWorld.isEmpty()) throw new MustBeNotEmptyException();
                        }
                        catch (MustBeNotEmptyException e)
                        {
                            System.out.println("In command <" + line + ">");
                            System.out.println("Chapter world must be not empty (11 argument)");
                            System.out.println("Fix script");
                            System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                            return false;
                        }
                        chapter.setWorld(chapterWorld);
                        space_marine_local.setChapter(chapter);

                        collectionManager.UPDATE(Integer.parseInt(id), space_marine_local);

                        return true;
                    }
                    else {
                        System.out.println("command <update> in script expect 11 arguments of object's attributes");
                        System.out.println("EXAMPLE:\nupdate [id] [name] [x] [y] [health] [astartes_category] [weapon_type] [melee_weapon] [chapter_name] [chapter_world]");
                        for (int i=1; i<total_script_command.length; i++)
                        {
                            System.out.println("unused argument -> " + total_script_command[i]);
                        }
                        return false;
                    }

                }
                if (total_script_command[0].equals("execute_script")) {
                    if (total_script_command.length == 2) {
                        String newFilePath = total_script_command[1];
                        if (processedFiles.contains(newFilePath))
                        {
                            System.out.println("Detected recursive call for file <" + newFilePath + ">");
                            return false;
                        }
                        else
                        {
                            if (!script_command.launch(total_script_command)){
                                System.out.println("Fix script, command <" + total_script_command[0] + "> not found");
                            }
                        }

                    }


                }
                else {
                    if (!script_command.launch(total_script_command)){
                        System.out.println("Fix script, command <" + total_script_command[0] + "> not found");
                    }
                }

            }
            processedFiles.remove(filePath);
            return true;
        }
        else
        {
            System.out.println("-----=[ <" + getName() + "> expect 1 argument ]=-----");
            return false;
        }

    }
}





