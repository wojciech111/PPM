package service.categorization;

import model.categorization.AreaOfFocus;
import model.categorization.Category;
import model.inventory.Portfolio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Test;
import service.inventory.InventoryService;
import util.HibernateUtil;
import util.exception.InvalidParentComponentException;
import util.exception.OutOfRangeException;


import static org.junit.Assert.*;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CategorizationServiceIntegrationTests {
    /*@After
    public void clearDataFromDatabase() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();

            session.createQuery("delete from Component").executeUpdate();
            session.createQuery("delete from Portfolio").executeUpdate();
            session.createQuery("delete from Program").executeUpdate();
            session.createQuery("delete from Project ").executeUpdate();
            //session.createQuery("delete from Category ").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }*/
    @Test
    public void aNewCategoryShouldBeCreated(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna","opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        assertTrue(category.getCode().startsWith("CA1"));
        assertEquals("Kategoria wyborna", category.getName());
        assertEquals("opis kategorii ktory jest niezwykle wyczerpuj¹cy", category.getDescription());
        assertNotEquals(category.getId(), 0);
    }
    @Test
    public void categoryShouldBeTakenById() {
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna","opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category categoryById = categorizationService.getCategory(category.getId());
        assertTrue(categoryById.getCode().startsWith(category.getCode()));
        assertEquals(categoryById.getName(), category.getName());
        assertEquals(categoryById.getDescription(), category.getDescription());
        assertEquals(categoryById.getId(), category.getId());

    }
    @Test
    public void categoryShouldBeUpdated() {
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        category.setCode("PFFF");
        category.setName("NOWY GrasshosT");
        category = categorizationService.updateCategory(category);
        Category categoryById = categorizationService.getCategory(category.getId());
        assertTrue(categoryById.getCode().startsWith("PFFF"));
        assertEquals(categoryById.getName(), "NOWY GrasshosT");
        assertEquals(categoryById.getDescription(), category.getDescription());
        assertEquals(categoryById.getId(), category.getId());
    }
    @Test
    public void categoryShouldBeDeleted(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category categoryById = categorizationService.getCategory(category.getId());
        categorizationService.deleteCategory(category);
        Category categoryByIdDeleted = categorizationService.getCategory(category.getId());
        assertNotNull(categoryById);
        assertNull(categoryByIdDeleted);
    }
    //AREA OF FOCUS
    @Test
    public void aNewAreaOfFocusShouldBeCreated() throws OutOfRangeException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.setAreaOfFocus(portfolio, category, (short) 5);
        assertEquals(areaOfFocus.getCategory().getId(),category.getId());
        assertEquals(areaOfFocus.getPortfolio().getId(), portfolio.getId());
        assertEquals(areaOfFocus.getPercentageOfFocus().intValue(), 5);
        assertEquals( portfolio.getAreasOfFocus().size(),1);
        assertEquals(portfolio.getAreasOfFocus().iterator().next().getCategory().getId(),category.getId());
    }
    /*@Test
    public void getAllPortfolioAreasOfFocus() throws OutOfRangeException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.setAreaOfFocus(portfolio, category, (short) 5);
        portfolio=categorizationService.getAreasOfFocusWithCategories(portfolio);
        assertEquals(portfolio.getAreasOfFocus().size(),1);


    }*/
}
