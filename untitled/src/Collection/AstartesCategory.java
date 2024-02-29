package Collection;


public enum AstartesCategory {
    ASSAULT,
    SUPPRESSOR,
    CHAPLAIN;

/**
* @brief        Simply lists the
*               elements that can
*               be entered when
*               initializing objects
* @param        void
* @return       String list of
*               AstartesCategory
*               elements
*/
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (AstartesCategory category : values()) {
            nameList.append(category.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
