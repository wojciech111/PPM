package model.inventory.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum RecursionType {
    D  ("Daily"),
    O ("Once"),
    M ("Monthly"),
    Y ("Yearly")
    ;
    private final String name;

    RecursionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
