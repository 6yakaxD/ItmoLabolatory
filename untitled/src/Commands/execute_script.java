package Commands;


import CustomExeptions.*;
import Tools.CollectionManager;
import Tools.CommandManager;
import Collection.SpaceMarine;
import Collection.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import static Collection.SpaceMarine.validateAllValues;

public class execute_script extends ACommand
{
    CommandManager commandManager;
    Scanner scanner;
    CollectionManager collectionManager;

    public static List<String> script_used_recursion = new ArrayList<>();

    public execute_script(CommandManager commandManager, Scanner scanner, CollectionManager collectionManager)
    {
        super("execute_script [file_path]", "read and execute the script from the specified file");
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.collectionManager = collectionManager;
    }

    public boolean execute_insert(List<String> insertLines)
    {
        if(insertLines.size()!=9)
        {
            System.out.println("Error in Insert script command");
            System.exit(0);
        }
        SpaceMarine spaceMarine = new SpaceMarine();

        String name = null;
        int x = 0;
        float y = 0;
        Coordinates coord = new Coordinates();
        double health = 0;
        AstartesCategory astartesCategory = null;
        Weapon weapon = null;
        MeleeWeapon meleeWeapon = null;
        String chapterName = null;
        String chapterWorld = null;
        Chapter chapter = new Chapter();

        try
        {
            name = insertLines.get(0).trim();
            if (name.isEmpty()) throw new MustBeNotEmptyException();
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("The name can't be empty");
            System.exit(0);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("The name can't be loaded or recognized");
            System.exit(0);
            if (!scanner.hasNext())
            {
                System.out.println("Ctrl-D exit!");
                System.exit(0);
            }
        }
        catch (IllegalStateException e)
        {
            System.out.println("Unexpected error!");
            System.exit(0);
        }
        try
        {
            String s = insertLines.get(1).trim();
            if (s.isEmpty()) throw new MustBeNotEmptyException();
            x = Integer.parseInt(s);
        }
        catch (NumberFormatException  e)
        {
            System.out.println("X must be an integer");
            System.exit(0);
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("X must be not empty");
            System.exit(0);
        }
        try
        {
            String s = insertLines.get(2).trim();
            if (s.isEmpty()) throw new MustBeNotEmptyException();
            y = Float.parseFloat(s);
            if (y > 617) throw new MustBeLessThan617();
        }
        catch (NumberFormatException e)
        {
            System.out.println("Y must be a float");
            System.exit(0);
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Y must be not empty");
            System.exit(0);
        }
        catch (MustBeLessThan617 e)
        {
            System.out.println("Y must be less 617");
            System.exit(0);
        }
        coord.setY(y);
        coord.setX(x);
        try
        {
            String s = insertLines.get(3).trim();
            if (s.isEmpty()) throw new MustBeNotEmptyException();
            health = Double.parseDouble(s);
            if (health <= 0) throw new MustBeMoreThanZero();
        }
        catch (MustBeMoreThanZero e)
        {
            System.out.println("Health value must be more than 0");
            System.exit(0);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Health must be a float");
            System.exit(0);
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Health must be not empty");
            System.exit(0);
        }
        try
        {
            String s = insertLines.get(4).toUpperCase().trim();
            if(s.isEmpty()) throw new MustBeNotEmptyException();
            astartesCategory = AstartesCategory.valueOf(s.toUpperCase());
        }
        catch (NoSuchElementException exception)
        {
            System.out.println("Type can't be recognized");
            System.exit(0);
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("There is no such AstartesCategory value in the category");
            System.exit(0);
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Astartes Category must be not empty");
            System.exit(0);
        }
        try
        {
            String s = insertLines.get(5).toUpperCase().trim();
            if(s.isEmpty()) throw new MustBeNotEmptyException();
            weapon = Weapon.valueOf(s.toUpperCase());
        }
        catch (NoSuchElementException exception)
        {
            System.out.println("Type can't be recognized");
            System.exit(0);
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("There is no such WeaponType value in the category");
            System.exit(0);
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Weapon Type must be not empty");
            System.exit(0);
        }
        try
        {
            String s = insertLines.get(6).toUpperCase().trim();
            if(s.isEmpty()) throw new MustBeNotEmptyException();;
            meleeWeapon = MeleeWeapon.valueOf(s.toUpperCase());
        }
        catch (NoSuchElementException exception)
        {
            System.out.println("Type can't be recognized");
            System.exit(0);
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("There is no such MeleeWeapon value in the category");
            System.exit(0);
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Melee Weapon must be not empty");
            System.exit(0);
        }
        try
        {
            chapterName = insertLines.get(7).trim();
            if (chapterName.isEmpty()) throw new MustBeNotEmptyException();
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Chapter Name must be not empty");
            System.exit(0);
        }
        try
        {
            chapterWorld = insertLines.get(8).trim();
            if (chapterWorld.isEmpty()) throw new MustBeNotEmptyException();
        }
        catch (MustBeNotEmptyException e)
        {
            System.out.println("Chapter world must be not empty");
            System.exit(0);
        }

        spaceMarine.setId(collectionManager.generate_new_id_for_element());
        spaceMarine.setName(name);
        coord.setX(x);
        coord.setY(y);
        spaceMarine.setCoordinates(coord);
        spaceMarine.setCreationDate(LocalDateTime.now());
        spaceMarine.setHealth(health);
        spaceMarine.setAstartesCategory(astartesCategory);
        spaceMarine.setWeaponType(weapon);
        spaceMarine.setMeleeWeapon(meleeWeapon);
        chapter.setName(chapterName);
        chapter.setWorld(chapterName);
        spaceMarine.setChapter(chapter);

        if(!validateAllValues(spaceMarine)){
            System.out.println("-----=[ Not validated data ]=-----");
            System.exit(0);
            return false;
        }
        else {
            collectionManager.addElement(spaceMarine);
            return true;
        }

    }


    @Override
    public boolean launch(String[] command) throws IOException {
        if (command.length == 2)
        {
            try
            {
                var file_script = new File(command[1]);
                if (!file_script.canRead()) throw new NotEnoughRightsReadException();
                Scanner userScanner = new Scanner(file_script);

                script_used_recursion.add(command[1].trim());

                if (!userScanner.hasNext()) throw new EmptyFileException();

                while (userScanner.hasNext()) {
                    String stroka_iter = userScanner.nextLine();
                    if (stroka_iter.isEmpty() || stroka_iter.trim().isEmpty()) {
                        continue;
                    }
                    String[] script_command_line = stroka_iter.trim().split("\\s+");


                    System.out.printf("~~~~~ executing command <%s> ~~~~~\n", stroka_iter.trim());
                    if (script_command_line[0].isEmpty()) {
                        continue;
                    }


                    if (script_command_line[0].equals("insert"))
                    {

                        List<String> insertLines = new ArrayList<>();
                        for (int i = 0; i < 9 && userScanner.hasNext(); i++) {
                            insertLines.add(userScanner.nextLine());
                        }

//                        for (String s: insertLines)
//                        {
//                            System.out.println(s);
//                        }
//                        System.out.println(insertLines.size());
                        //execute_insert(insertLines)
                        if (execute_insert(insertLines))
                        {
                            continue;
                        }
                        else
                        {
                            System.out.println("Error in Insert script command ");
                            System.exit(0);
                        }

                    }


                    if (script_command_line[0].equals("execute_script"))
                    {
                        if (script_used_recursion.contains(script_command_line[1])) {
                            script_used_recursion.clear();
                            throw new ScriptRecursionException();
                        } else {
                            script_used_recursion.add(script_command_line[1]);
                        }
                    }


                    ACommand ACom = commandManager.getCommand(script_command_line[0]);

                    if (ACom != null) {
                        ACom.launch(script_command_line);
                    } else {
                        System.out.println("-----=[ <" + script_command_line[0]+ "> not found use <help> ]=-----");
                    }

                }

                script_used_recursion.remove(command[1]);

                System.out.printf("-----=[ <%s> script execution completed ]=-----\n" ,command[1]);
            }
            catch (ScriptRecursionException exception)
            {
                System.out.println("-----=[ script recursion error ]=-----");
            }
            catch (NotEnoughRightsReadException exception)
            {
                System.out.println("-----=[ not enough rights file error ]=-----");
            }
            catch (EmptyFileException exception)
            {
                System.out.println("-----=[ file empty error ]=-----");
            }
            catch (FileNotFoundException exception)
            {
                System.out.println("-----=[ file not found ]=-----");
            }
            catch (IllegalStateException exception)
            {
                System.out.println("-----=[ undefined error ]=-----");
            }
//            catch (ErrorInsertCommandInScript e)
//            {
//                System.out.println("-----=[ insert error not correct input ]=-----");
//            }
            finally
            {
                script_used_recursion.clear();
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





