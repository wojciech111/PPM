package service.inventory;

import model.inventory.*;
import model.inventory.enums.CustomerType;
import model.inventory.enums.OperationType;
import model.inventory.enums.RecursionType;
import model.organization.Organization;
import model.process.*;
import model.process.Process;
import util.exception.InvalidParentComponentException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-07-05.
 */
public interface InventoryServiceInterface {
    //PORTFOLIO
    Portfolio createPortfolio(String code, String name, String customer, String description, Portfolio parent, Organization organization);

    Portfolio createPortfolio(String code, String name, CustomerType customerType, String customer, String sponsor,
                              String manager, String purpose, String description,
                              Timestamp creationDate, String createdBy,
                              Timestamp updateDate, String updatedBy,
                              Component parent, State state, Organization organization);

    Portfolio getPortfolio(long id);

    Portfolio updatePortfolio(Portfolio portfolio);

    void deletePortfolio(Portfolio portfolio);

    //PROGRAM
    Program createProgram(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException;

    public Program createProgram(String code, String name, CustomerType customerType,
                                 String customer, String sponsor, String manager,
                                 String purpose, String description,
                                 Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                                 Component parent, State state,Process process,
                                 String health, String scope, String schedule, String budget,
                                 Date startDate, Date endDate, Date deadlineDate,
                                 BigDecimal costOfStart, BigDecimal costOfStop,
                                 BigDecimal costOfRestart, BigDecimal costOfClose) throws InvalidParentComponentException ;

    Program getProgram(long id);

    Program updateProgram(Program program);

    void deleteProgram(Program program);

    //PROJECT
    Project createProject(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException;

    Project createProject(String code, String name, CustomerType customerType,
                          String customer, String sponsor, String manager,
                          String purpose, String description,
                          Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                          Component parent, State state, Process process,
                          String health, String scope, String schedule, String budget,
                          Date startDate, Date endDate, Date deadlineDate) throws InvalidParentComponentException;

    Project getProject(long id);

    Project updateProject(Project project);

    void deleteProject(Project project);

    //OPERATION
    Operation createOperation(String code, String name, String customer, String description, Component parent, RecursionType recursionType) throws InvalidParentComponentException;
    Operation createOperation(String code, String name, CustomerType customerType,
                              String customer, String sponsor, String manager,
                              String purpose, String description,
                              Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                              Component parent, State state,Process process,
                              OperationType operationType, RecursionType recursionType, Integer numberOfTimeUnits)
            throws InvalidParentComponentException;

    Operation getOperation(long id);

    Operation updateOperation(Operation operation);

    void deleteOperation(Operation operation);

}
