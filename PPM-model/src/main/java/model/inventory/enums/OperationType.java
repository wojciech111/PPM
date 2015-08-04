package model.inventory.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum OperationType {
    S  ("On start"),
    E ("On end"),
    P ("Proportional")
    ;
    private final String name;

    OperationType(String name) {
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
