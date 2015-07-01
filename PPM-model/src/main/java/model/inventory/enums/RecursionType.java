package model.inventory.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum RecursionType {
    /*
    d - daily
    w - weekly
    m - monthly
    q - quarterly - do poprawy spelling w db
    y - yearly'
     */

    D  ("Daily"),
    W ("Weekly"),
    M ("Monthly"),
    Q ("Quarterly"),
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
