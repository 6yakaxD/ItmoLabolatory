package Collection;

public enum AstartesCategory {
    ASSAULT,
    SUPPRESSOR,
    CHAPLAIN;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (AstartesCategory category : values()) {
            nameList.append(category.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
