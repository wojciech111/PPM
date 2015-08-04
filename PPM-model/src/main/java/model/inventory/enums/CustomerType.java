package model.inventory.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum CustomerType {
    IN  ("Internal"),
    EX ("External")
    ;
    private final String name;

    CustomerType(String name) {
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
