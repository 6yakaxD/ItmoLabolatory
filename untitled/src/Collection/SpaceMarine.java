package Collection;

import java.time.LocalDateTime;

public class SpaceMarine implements Comparable<SpaceMarine>
{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Coordinates getCoordinates()
    {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }
    public LocalDateTime getCreationDate()
    {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate)
    {
        this.creationDate = creationDate;
    }
    public double getHealth() {
        return health;
    }
    public void setHealth(double health)
    {
        this.health = health;
    }
    public AstartesCategory getAstartesCategory()
    {
        return category;
    }
    public void setAstartesCategory(AstartesCategory category)
    {
        this.category = category;
    }
    public Weapon getWeaponType()
    {
        return weaponType;
    }
    public void setWeaponType(Weapon weaponType)
    {
        this.weaponType = weaponType;
    }
    public MeleeWeapon getMeleeWeapon()
    {
        return meleeWeapon;
    }
    public void setMeleeWeapon(MeleeWeapon meleeWeapon)
    {
        this.meleeWeapon = meleeWeapon;
    }
    public Chapter getChapter() {
        return chapter;
    }
    public void setChapter(Chapter chapter)
    {
        this.chapter = chapter;
    }


    /**
     * @brief        Validator of all object field
     *               according lab condition
     * @param        spaceMarine -> SpaceMarine main
     *                              object's class
     * @return       bool value depending on the
     *               correct validation
     */
    public static boolean validateAllValues(SpaceMarine spaceMarine)
    {
        if (spaceMarine.id <= 0) return false;
        if (spaceMarine.name == null || spaceMarine.name.isEmpty()) return false;
        if ((spaceMarine.coordinates == null) || (spaceMarine.coordinates.getY() == null) || (spaceMarine.coordinates.getY() > 617) ) return false;
        if (spaceMarine.creationDate == null ) return false;
        if (spaceMarine.health <= 0) return false;
        if (spaceMarine.category == null) return false;
        if (spaceMarine.weaponType == null) return false;
        if (spaceMarine.meleeWeapon == null) return false;
        if (spaceMarine.chapter == null) return false;

        return true;
    }

    /**
     * @brief        Updator of all object field
     *               according lab condition
     * @param        spaceMarine -> SpaceMarine main
     *                              object's class
     * @return       void
     */
    public void update(SpaceMarine spaceMarine) {
        this.name = spaceMarine.name;
        this.coordinates = spaceMarine.coordinates;
        this.creationDate = spaceMarine.creationDate;
        this.health = spaceMarine.health;
        this.category = spaceMarine.category;
        this.weaponType = spaceMarine.weaponType;
        this.meleeWeapon = spaceMarine.meleeWeapon;
        this.chapter = spaceMarine.chapter;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (health <= 0 && o.getHealth() <= 0) return 0;
        if (health <= 0) return -1;
        if (o.getHealth() <= 0) return 1;

        double delta = health - o.getHealth();
        if (delta > 0) return 1;
        if (delta == 0) return 0;
        return -1;
    }

    @Override
    public String toString()
    {

        String ans = "\n-----=[ " + getName() + " ]=-----" +
                "\nId: " + getId() +
                "\nName: " + getName() +
                "\nCoordinates: (" + getCoordinates().getX() + ";" + getCoordinates().getY() + ")" +
                "\nCreation Date: " + getCreationDate()+
                "\nHealth: " + getHealth() +
                "\nAstartes Category: " + getAstartesCategory() +
                "\nWeapon Type: " + getWeaponType() +
                "\nMelee Weapon: " + getMeleeWeapon() +
                "\nChapter: " +
                "\n\tname: " + getChapter().getName() +
                "\n\tworld: " + getChapter().getWorld();

        return ans;
    }

}

