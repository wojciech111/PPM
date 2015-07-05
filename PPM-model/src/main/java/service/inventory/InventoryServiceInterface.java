package service.inventory;

import model.inventory.*;
import model.inventory.enums.RecursionType;
import util.exception.InvalidParentComponentException;

/**
 * Created by Wojciech on 2015-07-05.
 */
public interface InventoryServiceInterface {
    //PORTFOLIO
    Portfolio createPortfolio(String code, String name, String customer, String description, Portfolio parent);

    Portfolio getPortfolio(long id);

    Portfolio updatePortfolio(Portfolio portfolio);

    void deletePortfolio(Portfolio portfolio);

    //PROGRAM
    Program createProgram(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException;

    Program getProgram(long id);

    Program updateProgram(Program program);

    void deleteProgram(Program program);

    //PROJECT
    Project createProject(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException;

    Project getProject(long id);

    Project updateProject(Project project);

    void deleteProject(Project project);

    //OPERATION
    Operation createOperation(String code, String name, String customer, String description, Component parent, RecursionType recursionType) throws InvalidParentComponentException;

    Operation getOperation(long id);

    Operation updateOperation(Operation operation);

    void deleteOperation(Operation operation);
}
