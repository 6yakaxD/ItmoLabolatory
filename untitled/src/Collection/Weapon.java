package Collection;

public enum Weapon {
    HEAVY_BOLTGUN,
    MELTAGUN,
    BOLT_RIFLE;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Weapon weapon : values()) {
            nameList.append(weapon.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
