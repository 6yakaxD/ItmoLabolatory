package Tools;

import Collection.AstartesCategory;
import Collection.MeleeWeapon;
import Collection.Weapon;
import CustomExeptions.MustBeLessThan617;
import CustomExeptions.MustBeMoreThanZero;
import CustomExeptions.MustBeNotEmptyException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GetCollectionElementInfoFromConsoleAsker
{
    Scanner scanner = new Scanner(System.in);

    public enum AttributesToChange
    {
        Name,
        Coordinates,
        Health,
        WeaponType,
        MeleeWeapon,
        Chapter;

        public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (AttributesToChange weapon : values()) {
            nameList.append(weapon.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-2);
    }
    }

    public AttributesToChange askAttributesToChange()
    {
        AttributesToChange attributesToChange;
        while (true)
        {
            try
            {
                System.out.println("Enter the Attribute from the list: ");
                System.out.println(AttributesToChange.nameList());
                String s = scanner.nextLine().trim();
                if(s.isEmpty()) throw new MustBeNotEmptyException();
                attributesToChange = AttributesToChange.valueOf(s);
                break;
            }
            catch (NoSuchElementException exception)
            {
                System.out.println("Type can't be recognized");
            }
            catch (IllegalArgumentException exception)
            {
                System.out.println("There is no such value in the category");
            }
            catch (MustBeNotEmptyException exception)
            {
                System.out.println("Must be not empty");
            }
        }
        return attributesToChange;
    }

    public String askName()
    {
        String name;
        while (true)
        {
            System.out.print("Name: ");
            try
            {
                name = scanner.nextLine().trim();
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                break;
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("The name can't be empty");
            }
            catch (NoSuchElementException e)
            {
                System.out.println("The name can't be loaded or recognized");
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
        }
        return name;
    }

    public int askX()
    {
        int x;
        while (true)
        {
            try
            {
                System.out.print("X:");
                String s = scanner.nextLine().trim();
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                x = Integer.parseInt(s);
                break;
            }
            catch (NumberFormatException  e)
            {
                System.out.println("X must be an integer");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("X must be not empty");
            }
        }
        return x;
    }

    public Float askY()
    {
        float y;
        while (true)
        {
            try
            {
                System.out.print("Y: ");
                String s = scanner.nextLine().trim();
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                y = Float.parseFloat(s);
                if (y > 617) throw new MustBeLessThan617();
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Y must be a float");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Y must be not empty");
            }
            catch (MustBeLessThan617 e)
            {
                System.out.println("Y must be less 617");
            }
        }
        return y;
    }

    public LocalDateTime askCreationDate()
    {
        while (true)
        {
            try
            {
                return LocalDateTime.now();
            }
            catch (DateTimeException e)
            {
                System.out.println("Problem with local data");
            }
        }
    }

    public double askHealth()
    {
        double health;
        while (true)
        {
            try
            {
                System.out.print("Health: ");
                String s = scanner.nextLine().trim();
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                health = Double.parseDouble(s);
                if (health <= 0) throw new MustBeMoreThanZero();
                break;
            }
            catch (MustBeMoreThanZero e)
            {
                System.out.println("Health value must be more than 0");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Health must be a float");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Health must be not empty");
            }
        }
        return health;
    }

    public AstartesCategory askAstartesCategory()
    {
        AstartesCategory astartesCategory;
        while (true)
        {
            try
            {
                System.out.println("Enter the Astartes Category from the list: ");
                System.out.println("Categories: " + AstartesCategory.nameList());
                String s = scanner.nextLine().trim();
                if(s.isEmpty()) throw new MustBeNotEmptyException();
                astartesCategory = AstartesCategory.valueOf(s.toUpperCase());
                break;
            }
            catch (NoSuchElementException exception)
            {
                System.out.println("Type can't be recognized");
            }
            catch (IllegalArgumentException exception)
            {
                System.out.println("There is no such value in the category");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Astartes Category must be not empty");
            }
        }
        return astartesCategory;
    }

    public Weapon askWeapon()
    {
        Weapon weapon;
        while (true)
        {
            try
            {
                System.out.println("Enter the Weapon Type from the list: ");
                System.out.println("Types: " + Weapon.nameList());
                String s = scanner.nextLine().trim();
                if(s.isEmpty()) throw new MustBeNotEmptyException();
                weapon = Weapon.valueOf(s.toUpperCase());
                break;
            }
            catch (NoSuchElementException exception)
            {
                System.out.println("Type can't be recognized");
            }
            catch (IllegalArgumentException exception)
            {
                System.out.println("There is no such value in the category");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Weapon Type must be not empty");
            }
        }
        return weapon;
    }

    public MeleeWeapon askMeleeWeapon()
    {
        MeleeWeapon meleeWeapon;
        while (true)
        {
            try
            {
                System.out.println("Enter the Melee Weapon Type from the list: ");
                System.out.println("Types: " + MeleeWeapon.nameList());
                String s = scanner.nextLine().trim();
                if(s.isEmpty()) throw new MustBeNotEmptyException();;
                meleeWeapon = MeleeWeapon.valueOf(s.toUpperCase());
                break;
            }
            catch (NoSuchElementException exception)
            {
                System.out.println("Type can't be recognized");
            }
            catch (IllegalArgumentException exception)
            {
                System.out.println("There is no such value in the category");
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Health must be not empty");
            }
        }
        return meleeWeapon;
    }

    public String askChapterName()
    {
        String chapterName;
        while (true)
        {
            try
            {
                System.out.print("Chapter Name:");
                chapterName = scanner.nextLine().trim();
                if (chapterName.isEmpty()) throw new MustBeNotEmptyException();
                break;
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Chapter Name must be not empty");
            }
        }
        return chapterName;
    }

    public String askChapterWorld()
    {
        String chapterWorld;
        while (true)
        {
            try
            {
                System.out.print("Chapter World:");
                chapterWorld = scanner.nextLine().trim();
                if (chapterWorld.isEmpty()) throw new MustBeNotEmptyException();
                break;
            }
            catch (MustBeNotEmptyException e)
            {
                System.out.println("Chapter world must be not empty");
            }
        }
        return chapterWorld;
    }
}
