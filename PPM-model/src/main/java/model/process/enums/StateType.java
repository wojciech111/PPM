package model.process.enums;

/**
 * Created by Wojciech on 2015-06-26.
 */
public enum StateType {
    B  ("Begin"),
    E  ("Evaluation"),
    P  ("Planing"),
    V  ("Voting"),
    W  ("Waiting for resources"),
    X  ("Executing"),
    C ("Cancel"),
    D ("Delayed"),
    A ("Archive")
    ;
    private final String name;

    StateType(String name) {
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
