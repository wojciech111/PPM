package service;

import model.inventory.Component;
import model.inventory.Portfolio;
import model.inventory.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.inventory.InventoryService;
import service.inventory.InventoryServiceInterface;
import util.HibernateUtil;
import util.exception.InvalidParentComponentException;

import static org.junit.Assert.*;
/**
 * Created by Wojciech on 2015-08-12.
 */
public class CascadeTest {
    @After
    public void clearDataFromDatabase() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();



            session.createQuery("delete from AreaOfFocus").executeUpdate();
            session.createQuery("delete from CategoryMembership ").executeUpdate();
            session.createQuery("delete from CategoryEvaluation ").executeUpdate();
            session.createQuery("delete from Score ").executeUpdate();

            session.createQuery("delete from Category ").executeUpdate();
            session.createQuery("delete from ScoringCriterion ").executeUpdate();

            session.createQuery("delete from Component").executeUpdate();
            session.createQuery("delete from Portfolio").executeUpdate();
            session.createQuery("delete from Program").executeUpdate();
            session.createQuery("delete from Project ").executeUpdate();
            session.createQuery("delete from Operation ").executeUpdate();

            session.createQuery("delete from State ").executeUpdate();
            session.createQuery("delete from Process ").executeUpdate();
            session.createQuery("delete from Decision ").executeUpdate();

            session.createQuery("delete from Stakeholder ").executeUpdate();

            session.createQuery("delete from Employee ").executeUpdate();
            session.createQuery("delete from Organization  ").executeUpdate();
            session.createQuery("delete from User ").executeUpdate();


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
/*
    @Test
    public void parentShouldUpdate() throws InvalidParentComponentException {
        InventoryServiceInterface inventoryService = new InventoryService();
        Portfolio portfolio = inventoryService.createPortfolio("PT", "portfolioTop", "customer jakis", "Opis Opisik", null);
        Project portfolioProject = inventoryService.createProject("PF1", "portfolioProject", "customer jakis", "Opis Opisik", portfolio);
        portfolio.setName("NOWY GrasshosT");
        Portfolio updatedPortfolio = inventoryService.updatePortfolio(portfolio);
        Portfolio savedPortfolio = inventoryService.getPortfolio(portfolio.getId());
        assertEquals(updatedPortfolio.getName(), "NOWY GrasshosT");
        assertEquals(savedPortfolio.getName(), "NOWY GrasshosT");
    }
    @Test
    public void parentAndChildShouldUpdate() throws InvalidParentComponentException {
        InventoryServiceInterface inventoryService = new InventoryService();
        Portfolio portfolio = inventoryService.createPortfolio("PT", "portfolioTop", "customer jakis", "Opis Opisik", null);
        Project portfolioProject = inventoryService.createProject("PF1", "portfolioProject", "customer jakis", "Opis Opisik", portfolio);
        portfolio = inventoryService.getPortfolio(portfolio.getId());
        portfolio.setName("NOWY GrasshosT");
        portfolio.getChildren().iterator().next().setName("SUKCESUPDATED");
        Portfolio updatedPortfolio = inventoryService.updatePortfolio(portfolio);
        Component projectUpdatedPortfolio = updatedPortfolio.getChildren().iterator().next();
        Portfolio savedPortfolio = inventoryService.getPortfolio(portfolio.getId());
        Component projectSavedPortfolio = updatedPortfolio.getChildren().iterator().next();
        assertEquals(updatedPortfolio.getName(), "NOWY GrasshosT");
        assertEquals(savedPortfolio.getName(), "NOWY GrasshosT");
        assertEquals(projectUpdatedPortfolio.getName(), "SUKCESUPDATED");
        assertEquals(projectSavedPortfolio.getName(), "SUKCESUPDATED");
    }
    */
}
