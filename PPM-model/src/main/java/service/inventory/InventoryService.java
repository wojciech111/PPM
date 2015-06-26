package service.inventory;

import dao.inventory.PortfolioDAO;
import dao.inventory.ProgramDAO;
import dao.inventory.ProjectDAO;
import model.inventory.Component;
import model.inventory.Portfolio;
import model.inventory.Program;
import model.inventory.Project;
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
            portfolio.setParent(parentPortfolio);
            //parentPortfolio.getChildren().add(portfolio); that was wrong
        }

        portfolio = PortfolioDAO.save(portfolio);

        return portfolio;
    }

    public Portfolio getPortfolio(long id) {
        return PortfolioDAO.getById(id);
    }

    public Portfolio updatePortfolio(Portfolio portfolio) throws InvalidParentComponentException {
        if (portfolio.getParent() == null || portfolio.getParent() instanceof Portfolio)
            portfolio = PortfolioDAO.update(portfolio);
        else
            throw new InvalidParentComponentException("Portfolio can only be child of Portfolio.");
        return portfolio;
    }

    public void deletePortfolio(Portfolio portfolio) {
        PortfolioDAO.delete(portfolio);
    }


    //PROGRAM
    public Program createProgram(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException {
        Program program = new Program(code,name,customer,description);
        if(parent != null) {
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

    public Program updateProgram(Program program) throws InvalidParentComponentException {
        if (program.getParent() == null || program.getParent() instanceof Portfolio || program.getParent() instanceof Program)
            program = ProgramDAO.update(program);
        else
            throw new InvalidParentComponentException("Program can only be child of Portfolio or Program.");
        return program;
    }

    public void deleteProgram(Program program) {
        ProgramDAO.delete(program);
    }

    //PROJECT
    public Project createProject(String code, String name, String customer, String description, Component parent) throws InvalidParentComponentException {
        Project project = new Project(code,name,customer,description);
        if(parent != null) {
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

    public Project updateProject(Project project) throws InvalidParentComponentException {
        if (project.getParent() == null || project.getParent() instanceof Portfolio || project.getParent() instanceof Program)
            project = ProjectDAO.update(project);
        else
            throw new InvalidParentComponentException("Project can only be child of Portfolio or Program.");
        return project;
    }

    public void deleteProject(Project project) {
        ProjectDAO.delete(project);
    }

}

