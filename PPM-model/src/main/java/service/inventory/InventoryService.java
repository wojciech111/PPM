package service.inventory;

import dao.inventory.OperationDAO;
import dao.inventory.PortfolioDAO;
import dao.inventory.ProgramDAO;
import dao.inventory.ProjectDAO;
import model.inventory.*;
import model.inventory.enums.RecurssionType;
import util.exception.InvalidParentComponentException;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class InventoryService {

    //PORTFOLIO
    public Portfolio createPortfolio(String code, String name, String customer, String description, Portfolio parent) {
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
        return PortfolioDAO.update(portfolio);
    }

    public void deletePortfolio(Portfolio portfolio) {
        PortfolioDAO.delete(portfolio);
    }


    //PROGRAM
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
    public Operation createOperation(String code, String name, String customer, String description, Component parent, RecurssionType recurssionType) throws InvalidParentComponentException {
        Operation operation = new Operation(code,name,customer,description, recurssionType);
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

