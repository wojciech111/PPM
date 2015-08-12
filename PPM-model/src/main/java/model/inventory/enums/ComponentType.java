package model.inventory.enums;

import model.inventory.Operation;
import model.inventory.Portfolio;
import model.inventory.Program;
import model.inventory.Project;

/**
 * Created by Wojciech on 2015-07-09.
 */
public enum ComponentType {
    PORTFOLIO(Portfolio.class),
    PROGRAM(Program.class),
    PROJECT(Project.class),
    OPERATION(Operation.class);

    public final Class<?> clazz;

    ComponentType(Class<?> clazz) { this.clazz = clazz; }

    public static ComponentType forName(String name) {
        for (ComponentType t : values())
            if (name.equals(t.name()))
                return t;

        return null;
    }
}
