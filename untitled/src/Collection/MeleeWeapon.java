package Collection;

public enum MeleeWeapon {
    CHAIN_SWORD,
    POWER_SWORD,
    CHAIN_AXE,
    MANREAPER;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (MeleeWeapon meleeWeapon : values()) {
            nameList.append(meleeWeapon.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
