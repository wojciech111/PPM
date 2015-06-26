package service.inventory;

import java.util.List;

import model.inventory.Portfolio;
import model.inventory.Program;
import model.inventory.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Test;
import util.HibernateUtil;
import util.exception.InvalidParentComponentException;

import javax.sound.sampled.Port;

import static org.junit.Assert.*;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class InventoryServiceIntegrationTests {
    @After
    public void clearDataFromDatabase() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();

            session.createQuery("delete from Component").executeUpdate();
            session.createQuery("delete from Portfolio").executeUpdate();
            session.createQuery("delete from Program").executeUpdate();
            session.createQuery("delete from Project ").executeUpdate();
            session.createQuery("delete from Category ").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    //COMPONENT
    @Test(expected=InvalidParentComponentException.class)
    public void componentWithParentComponentShouldBeForbidenToUpdate() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project project = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        project.setCode("PFFF");
        project.setName("NOWY GrasshosT");
        project.setParent(project);
    }
    //PORTFOLIO
    @Test
    public void aNewPortfolioShouldBeCreated(){
        Portfolio portfolio = new InventoryService().createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        assertEquals("PF1", portfolio.getCode());
        assertEquals("GrassHost", portfolio.getName());
        assertEquals("Opis Opisik", portfolio.getDescription());
        assertNull(portfolio.getParent());
        assertNotEquals(portfolio.getId(), 0);
    }
    @Test
    public void portfolioShouldBeTakenById() {
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Portfolio portfolioById = inventoryService.getPortfolio(portfolio.getId());
        assertTrue(portfolioById.getCode().startsWith(portfolio.getCode()));
        assertEquals(portfolioById.getName(), portfolio.getName());
        assertEquals(portfolioById.getCustomer(), portfolio.getCustomer());
        assertEquals(portfolioById.getDescription(), portfolio.getDescription());
        assertNull(portfolioById.getParent());

    }

    @Test
    public void portfolioShouldBeUpdated() {
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        portfolio.setCode("PFFF");
        portfolio.setName("NOWY GrasshosT");
        portfolio = inventoryService.updatePortfolio(portfolio);
        Portfolio portfolioById = inventoryService.getPortfolio(portfolio.getId());
        assertTrue(portfolioById.getCode().startsWith("PFFF"));
        assertEquals(portfolioById.getName(), "NOWY GrasshosT");
        assertEquals(portfolioById.getCustomer(), portfolio.getCustomer());
        assertEquals(portfolioById.getDescription(), portfolio.getDescription());
        assertNull(portfolioById.getParent());
    }
    @Test(expected=InvalidParentComponentException.class)
    public void portfolioWithParentProgramShouldBeForbidenToUpdate() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        portfolio.setCode("PFFF");
        portfolio.setName("NOWY GrasshosT");
        Program program = inventoryService.createProgram("PF2", "Karczemka", "customer jakis", "Opis Opisik", null);
        portfolio.setParent(program);
    }
    @Test
    public void portfolioShouldBeDeleted(){
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Portfolio portfolioById = inventoryService.getPortfolio(portfolio.getId());
        inventoryService.deletePortfolio(portfolio);
        Portfolio portfolioByIdDeleted = inventoryService.getPortfolio(portfolio.getId());
        assertNotNull(portfolioById);
        assertNull(portfolioByIdDeleted);
    }
    @Test
    public void aNewPortfolioWithSubportfolioShouldBeCreated() {
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolioParent = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Portfolio portfolioChild = inventoryService.createPortfolio("PF2", "Karczemka", "customer jakis", "Opis Opisik", portfolioParent);
        portfolioParent = inventoryService.getPortfolio(portfolioParent.getId());

        assertNotNull(portfolioChild.getParent());
        assertEquals(portfolioChild.getParent().getId(), portfolioParent.getId());

        assertEquals(portfolioParent.getChildren().size(), 1);
    }

    //PROGRAM

    @Test
    public void aNewProgramShouldBeCreated() throws InvalidParentComponentException {
        Program program = new InventoryService().createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        assertEquals("PF1", program.getCode());
        assertEquals("GrassHost", program.getName());
        assertEquals("Opis Opisik", program.getDescription());
        assertNull(program.getParent());
        assertNotEquals(program.getId(), 0);
    }
    @Test(expected=InvalidParentComponentException.class)
    public void aNewProjectWithSubprogramShouldBeForbidenToCreate() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project projectParent = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program programChild = inventoryService.createProgram("PF2", "Karczemka", "customer jakis", "Opis Opisik", projectParent);
    }
    @Test
    public void programShouldBeTakenById() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Program program = inventoryService.createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program programById = inventoryService.getProgram(program.getId());
        assertTrue(programById.getCode().startsWith(program.getCode()));
        assertEquals(programById.getName(), program.getName());
        assertEquals(programById.getCustomer(), program.getCustomer());
        assertEquals(programById.getDescription(), program.getDescription());
        assertNull(programById.getParent());

    }
    @Test
    public void programShouldBeUpdated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Program program = inventoryService.createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        program.setCode("PFFF");
        program.setName("NOWY GrasshosT");
        program = inventoryService.updateProgram(program);
        Program programById = inventoryService.getProgram(program.getId());
        assertTrue(programById.getCode().startsWith("PFFF"));
        assertEquals(programById.getName(), "NOWY GrasshosT");
        assertEquals(programById.getCustomer(), program.getCustomer());
        assertEquals(programById.getDescription(), program.getDescription());
        assertNull(programById.getParent());
    }
    @Test(expected=InvalidParentComponentException.class)
    public void programWithParentProjectShouldBeForbidenToUpdate() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Program program = inventoryService.createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        program.setCode("PFFF");
        program.setName("NOWY GrasshosT");
        Project project = inventoryService.createProject("PF2", "Karczemka", "customer jakis", "Opis Opisik", null);
        program.setParent(project);
    }
    @Test
    public void programShouldBeDeleted() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Program program = inventoryService.createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program programById = inventoryService.getProgram(program.getId());
        inventoryService.deleteProgram(program);
        Program programByIdDeleted = inventoryService.getProgram(program.getId());
        assertNotNull(programById);
        assertNull(programByIdDeleted);
    }
    @Test
    public void aNewProgramWithSubprogramShouldBeCreated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Program programParent = inventoryService.createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program programChild = inventoryService.createProgram("PF2", "Karczemka", "customer jakis", "Opis Opisik", programParent);
        programParent = inventoryService.getProgram(programParent.getId());

        assertNotNull(programChild.getParent());
        assertEquals(programChild.getParent().getId(), programParent.getId());

        assertEquals(programParent.getChildren().size(), 1);
    }
    @Test
    public void aNewPortfolioWithSubprogramShouldBeCreated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolioParent = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program programChild = inventoryService.createProgram("PF2", "Karczemka", "customer jakis", "Opis Opisik", portfolioParent);
        portfolioParent = inventoryService.getPortfolio(portfolioParent.getId());

        assertNotNull(programChild.getParent());
        assertEquals(programChild.getParent().getId(), portfolioParent.getId());

        assertEquals(portfolioParent.getChildren().size(),1);
    }

    //PROJECT

    @Test
    public void aNewProjectShouldBeCreated() throws InvalidParentComponentException {
        Project project = new InventoryService().createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        assertEquals("PF1", project.getCode());
        assertEquals("GrassHost", project.getName());
        assertEquals("Opis Opisik", project.getDescription());
        assertNull(project.getParent());
        assertNotEquals(project.getId(), 0);
    }
    @Test(expected=InvalidParentComponentException.class)
    public void aNewProjectWithSubprojectShouldBeForbidenToCreate() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project projectParent = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Project projectChild = inventoryService.createProject("PF2", "Karczemka", "customer jakis", "Opis Opisik", projectParent);
    }
    @Test
    public void projectShouldBeTakenById() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project project = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Project projectById = inventoryService.getProject(project.getId());
        assertTrue(projectById.getCode().startsWith(project.getCode()));
        assertEquals(projectById.getName(), project.getName());
        assertEquals(projectById.getCustomer(), project.getCustomer());
        assertEquals(projectById.getDescription(), project.getDescription());
        assertNull(projectById.getParent());

    }
    @Test
    public void projectShouldBeUpdated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project project = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        project.setCode("PFFF");
        project.setName("NOWY GrasshosT");
        project = inventoryService.updateProject(project);
        Project projectById = inventoryService.getProject(project.getId());
        assertTrue(projectById.getCode().startsWith("PFFF"));
        assertEquals(projectById.getName(), "NOWY GrasshosT");
        assertEquals(projectById.getCustomer(), project.getCustomer());
        assertEquals(projectById.getDescription(), project.getDescription());
        assertNull(projectById.getParent());
    }
    @Test(expected=InvalidParentComponentException.class)
    public void projectWithParentProjectShouldBeForbidenToUpdate() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project project = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        project.setCode("PFFF");
        project.setName("NOWY GrasshosT");
        Project project2 = inventoryService.createProject("PF2", "Karczemka", "customer jakis", "Opis Opisik", null);
        project.setParent(project2);
    }
    @Test
    public void projectShouldBeDeleted() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Project project = inventoryService.createProject("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Project projectById = inventoryService.getProject(project.getId());
        inventoryService.deleteProject(project);
        Project projectByIdDeleted = inventoryService.getProject(project.getId());
        assertNotNull(projectById);
        assertNull(projectByIdDeleted);
    }
    @Test
    public void aNewProgramWithSubprojectShouldBeCreated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Program programParent = inventoryService.createProgram("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Project projectChild = inventoryService.createProject("PF2", "Karczemka", "customer jakis", "Opis Opisik", programParent);
        programParent = inventoryService.getProgram(programParent.getId());

        assertNotNull(projectChild.getParent());
        assertEquals(projectChild.getParent().getId(), programParent.getId());

        assertEquals(programParent.getChildren().size(), 1);
    }
    @Test
    public void aNewPortfolioWithSubprojectShouldBeCreated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        Portfolio portfolioParent = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Project projectChild = inventoryService.createProject("PF2", "Karczemka", "customer jakis", "Opis Opisik", portfolioParent);
        portfolioParent = inventoryService.getPortfolio(portfolioParent.getId());

        assertNotNull(projectChild.getParent());
        assertEquals(projectChild.getParent().getId(), portfolioParent.getId());

        assertEquals(portfolioParent.getChildren().size(),1);
    }

    //OPERATION
}
