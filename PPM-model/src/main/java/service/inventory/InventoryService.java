package service.inventory;

import dao.inventory.OperationDAO;
import dao.inventory.PortfolioDAO;
import dao.inventory.ProgramDAO;
import dao.inventory.ProjectDAO;
import model.inventory.*;
import model.inventory.enums.CustomerType;
import model.inventory.enums.OperationType;
import model.inventory.enums.RecursionType;
import model.organization.Organization;
import model.process.State;
import util.exception.InvalidParentComponentException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class InventoryService implements InventoryServiceInterface {

    //PORTFOLIO
    public Portfolio createPortfolio(String code, String name, CustomerType customerType, String customer, String sponsor,
                                     String manager, String purpose, String description,
                                     Timestamp creationDate, String createdBy,
                                     Timestamp updateDate, String updatedBy,
                                     Component parent, State state, Organization organization) {
        Portfolio portfolio = new Portfolio( code,  name,  customerType,  customer,  sponsor,
                 manager,  purpose,  description,
                 creationDate,  createdBy,
                 updateDate,  updatedBy,
                 parent,  state,  organization);
        if(parent != null) {
            Portfolio parentPortfolio = PortfolioDAO.getById(parent.getId());
            try {
                portfolio.setParent(parentPortfolio);
            } catch (InvalidParentComponentException e) { // never happen

            }
            //parentPortfolio.getChildren().add(portfolio); that was wrong
        }

        portfolio = PortfolioDAO.save(portfolio);

        return portfolio;
    }
    public Portfolio createPortfolio(String code, String name, String customer, String description, Portfolio parent, Organization organization) {
        Portfolio portfolio = new Portfolio(code,name,customer,description);
        if(parent != null) {
            Portfolio parentPortfolio = PortfolioDAO.getById(parent.getId());
            try {
                portfolio.setParent(parentPortfolio);
            } catch (InvalidParentComponentException e) { // never happen

            }
            //parentPortfolio.getChildren().add(portfolio); that was wrong
        }

        portfolio = PortfolioDAO.save(portfolio);

        return portfolio;
    }

    public Portfolio getPortfolio(long id) {
        return PortfolioDAO.getById(id);
    }

    public Portfolio updatePortfolio(Portfolio portfolio){
        /*for (Component child:portfolio.getChildren()){
            if(child instanceof Project){
                child = updateProject((Project)child);
            }
        }*/
        return PortfolioDAO.update(portfolio);
    }


    public void deletePortfolio(Portfolio portfolio) {
        PortfolioDAO.delete(portfolio);
    }


    //PROGRAM
    public Program createProgram(String code, String name, CustomerType customerType,
                                 String customer, String sponsor, String manager,
                                 String purpose, String description,
                                 Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                                 Component parent, State state,
                                 String health, String scope, String schedule, String budget,
                                 Date startDate, Date endDate, Date deadlineDate,
                                 BigDecimal costOfStart, BigDecimal costOfStop,
                                 BigDecimal costOfRestart, BigDecimal costOfClose) throws InvalidParentComponentException {
        Program program = new Program(code, name, customerType, customer, sponsor, manager, purpose, description,
                creationDate, createdBy, updateDate, updatedBy, parent, state,health,  scope,  schedule,  budget,
                 startDate,  endDate,  deadlineDate,
                 costOfStart,  costOfStop,
                 costOfRestart,  costOfClose);
        if(parent == null) {
            throw new InvalidParentComponentException("Program need parent Portfolio or Program");
        }else{
            if(parent instanceof Portfolio){
                Portfolio parentPortfolio = PortfolioDAO.getById(parent.getId());
                program.setParent(parentPortfolio);
            }else if (parent instanceof Program){
                Program parentProgram = ProgramDAO.getById(parent.getId());
                program.setParent(parentProgram);
            } else {
                throw new InvalidParentComponentException("Program can only be child of Portfolio or Program.");
            }
        }

        program = ProgramDAO.save(program);

        return program;
    }
    public Program createProgram(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException {
        Program program = new Program(code,name,customer,description);
        if(parent == null) {
            throw new InvalidParentComponentException("Program need parent Portfolio or Program");
        }else{
            if(parent instanceof Portfolio){
                Portfolio parentPortfolio = PortfolioDAO.getById(parent.getId());
                program.setParent(parentPortfolio);
            }else if (parent instanceof Program){
                Program parentProgram = ProgramDAO.getById(parent.getId());
                program.setParent(parentProgram);
            } else {
                throw new InvalidParentComponentException("Program can only be child of Portfolio or Program.");
            }
        }

        program = ProgramDAO.save(program);

        return program;
    }

    public Program getProgram(long id) {
        return ProgramDAO.getById(id);
    }

    public Program updateProgram(Program program) {
        return ProgramDAO.update(program);
    }

    public void deleteProgram(Program program) {
        ProgramDAO.delete(program);
    }

    //PROJECT
    public Project createProject(String code, String name, CustomerType customerType,
                                 String customer, String sponsor, String manager,
                                 String purpose, String description,
                                 Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                                 Component parent, State state,
                                 String health, String scope, String schedule, String budget,
                                 Date startDate, Date endDate, Date deadlineDate) throws InvalidParentComponentException {
        Project project = new Project(code, name, customerType, customer, sponsor, manager, purpose, description,
                creationDate, createdBy, updateDate, updatedBy, parent, state,health,  scope,  schedule,  budget,
                startDate,  endDate,  deadlineDate);
        if(parent == null) {
            throw new InvalidParentComponentException("Project need parent Portfolio or Program");
        }else{
            if(parent instanceof Portfolio){
                Portfolio parentPortfolio = PortfolioDAO.getById(parent.getId());
                project.setParent(parentPortfolio);
            }else if (parent instanceof Program){
                Program parentProgram = ProgramDAO.getById(parent.getId());
                project.setParent(parentProgram);
            } else {
                throw new InvalidParentComponentException("Project can only be child of Portfolio or Program.");
            }
        }

        project = ProjectDAO.save(project);

        return project;
    }
    public Project createProject(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException {
        Project project = new Project(code,name,customer,description);
        if(parent == null) {
            throw new InvalidParentComponentException("Project need parent Portfolio or Program");
        }else{
            if(parent instanceof Portfolio){
                Portfolio parentPortfolio = PortfolioDAO.getById(parent.getId());
                project.setParent(parentPortfolio);
            }else if (parent instanceof Program){
                Program parentProgram = ProgramDAO.getById(parent.getId());
                project.setParent(parentProgram);
            } else {
                throw new InvalidParentComponentException("Project can only be child of Portfolio or Program.");
            }
        }

        project = ProjectDAO.save(project);

        return project;
    }

    public Project getProject(long id) {
        return ProjectDAO.getById(id);
    }

    public Project updateProject(Project project) {
        return ProjectDAO.update(project);
    }

    public void deleteProject(Project project) {
        ProjectDAO.delete(project);
    }
    //OPERATION
    public Operation createOperation(String code, String name, CustomerType customerType,
                                     String customer, String sponsor, String manager,
                                     String purpose, String description,
                                     Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                                     Component parent, State state,
                                     OperationType operationType, RecursionType recursionType, Integer numberOfTimeUnits) throws InvalidParentComponentException {
        Operation operation = new Operation(code, name, customerType, customer, sponsor, manager, purpose, description,
                creationDate, createdBy, updateDate, updatedBy, parent, state,operationType,recursionType,numberOfTimeUnits);
        if(parent == null) {
            throw new InvalidParentComponentException("Operation need parent Program");
        }else{
            if (parent instanceof Program){
                Program parentProgram = ProgramDAO.getById(parent.getId());
                operation.setParent(parentProgram);
            } else {
                throw new InvalidParentComponentException("Operation can only be child of Program.");
            }
        }

        operation = OperationDAO.save(operation);

        return operation;
    }
    public Operation createOperation(String code, String name, String customer, String description, Component parent, RecursionType recursionType) throws InvalidParentComponentException {
        Operation operation = new Operation(code,name,customer,description, recursionType);
        if(parent == null) {
            throw new InvalidParentComponentException("Operation need parent Program");
        }else{
            if (parent instanceof Program){
                Program parentProgram = ProgramDAO.getById(parent.getId());
                operation.setParent(parentProgram);
            } else {
                throw new InvalidParentComponentException("Operation can only be child of Program.");
            }
        }

        operation = OperationDAO.save(operation);

        return operation;
    }

    public Operation getOperation(long id) {
        return OperationDAO.getById(id);
    }

    public Operation updateOperation(Operation operation) {
        return OperationDAO.update(operation);
    }

    public void deleteOperation(Operation operation) {
        OperationDAO.delete(operation);
    }
}

