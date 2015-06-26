package model.inventory.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum RecurssionType {
    /*
    d - daily
    w - weekly
    m - monthly
    q - quaterly
    y - yearly'
     */

    DAILY  ("D"),
    WEEKLY ("W"),
    MONTHLY ("M"),
    QUATERLY ("Q"),
    YEARLY ("Y")
    ;
    private final String code;

    RecurssionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
