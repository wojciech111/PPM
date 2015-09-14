package model.process.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum DecisionState {
    P  ("Proposition"),
    R  ("Recommendation"),
    A  ("Approved"),
    E  ("Executed"),
    D  ("Discarded"),
    H  ("Archived")

    ;
    private final String name;

    DecisionState(String name) {
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
