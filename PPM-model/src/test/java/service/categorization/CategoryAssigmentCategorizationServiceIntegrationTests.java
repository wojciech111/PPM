package service.categorization;

import model.categorization.AreaOfFocus;
import model.categorization.Category;
import model.categorization.CategoryMembership;
import model.inventory.Portfolio;
import model.inventory.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Test;
import service.inventory.InventoryService;
import service.inventory.InventoryServiceInterface;
import util.HibernateUtil;
import util.exception.InvalidParentComponentException;
import util.exception.OutOfRangeException;
import util.exception.CannotBeMemberOfCategoryIfNotMemberOfPortfolioException;

import static org.junit.Assert.*;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CategoryAssigmentCategorizationServiceIntegrationTests {
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

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    //CATEGORY
    @Test
    public void aNewCategoryShouldBeCreated(){
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna","opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        assertTrue(category.getCode().startsWith("CA1"));
        assertEquals("Kategoria wyborna", category.getName());
        assertEquals("opis kategorii ktory jest niezwykle wyczerpuj¹cy", category.getDescription());
        assertNotEquals(category.getId(), 0);
    }
    @Test
    public void categoryShouldBeTakenById() {
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna","opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category categoryById = categorizationService.getCategory(category.getId());
        assertTrue(categoryById.getCode().startsWith(category.getCode()));
        assertEquals(categoryById.getName(), category.getName());
        assertEquals(categoryById.getDescription(), category.getDescription());
        assertEquals(categoryById.getId(), category.getId());

    }
    @Test
    public void categoryShouldBeUpdated() {
        CategorizationServiceInterface categorizationService = new CategorizationService();
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
        CategorizationServiceInterface categorizationService = new CategorizationService();
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
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.createAreaOfFocus(portfolio, category, (short) 5);
        assertEquals(areaOfFocus.getCategory().getId(),category.getId());
        assertEquals(areaOfFocus.getPortfolio().getId(), portfolio.getId());
        assertEquals(areaOfFocus.getPercentageOfFocus().intValue(), 5);
        assertEquals(portfolio.getAreasOfFocus().size(), 1);
        assertEquals(portfolio.getAreasOfFocus().iterator().next().getCategory().getId(), category.getId());
    }
    @Test(expected=OutOfRangeException.class)
    public void aNewAreaOfFocusShouldBeForbidenToCreateBecouseOutOfRange() throws OutOfRangeException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.createAreaOfFocus(portfolio, category, (short) 101);
    }
    @Test
    public void areaOfFocusShouldBeUpdated() throws OutOfRangeException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.createAreaOfFocus(portfolio, category, (short) 5);
        AreaOfFocus newAreaOfFocus = new AreaOfFocus(portfolio,category,(short)66);
        AreaOfFocus areaOfFocusFromUpdate = categorizationService.updateAreaOfFocus(newAreaOfFocus);

        Portfolio portfolioAfterUpdate = inventoryService.getPortfolio(portfolio.getId());

        assertEquals(portfolioAfterUpdate.getAreasOfFocus().size(), 1);
        assertEquals(portfolioAfterUpdate.getAreasOfFocus().iterator().next().getPercentageOfFocus().intValue(),66);
        //assertEquals(portfolio.getAreasOfFocus().iterator().next().getPercentageOfFocus().intValue(),66); //nie wiem jak to zrobiæ
    }
    @Test(expected=OutOfRangeException.class)
    public void areaOfFocusShouldBeForbidenToUpdateBecouseOutOfRange() throws OutOfRangeException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.createAreaOfFocus(portfolio, category, (short) 5);
        AreaOfFocus newAreaOfFocus = new AreaOfFocus(portfolio,category,(short)101);
        AreaOfFocus areaOfFocusFromUpdate = categorizationService.updateAreaOfFocus(newAreaOfFocus);
    }

    @Test
    public void areaOfFocusShouldBeDeleted() throws OutOfRangeException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        AreaOfFocus areaOfFocus = categorizationService.createAreaOfFocus(portfolio, category, (short) 5);
        categorizationService.deleteAreaOfFocus(areaOfFocus);

        Portfolio portfolioAfterUpdate = inventoryService.getPortfolio(portfolio.getId());

        assertEquals(portfolio.getAreasOfFocus().size(), 0);
        assertEquals(portfolioAfterUpdate.getAreasOfFocus().size(), 0);
    }

    //CATEGORY MEMBERSHIP

    @Test
    public void aNewCategoryMembershipShouldBeCreated() throws InvalidParentComponentException, CannotBeMemberOfCategoryIfNotMemberOfPortfolioException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        CategoryMembership categoryMembership = categorizationService.createCategoryMembership(program, category);

        assertEquals(categoryMembership.getCategory().getId(),category.getId());
        assertEquals(categoryMembership.getComponent().getId(), program.getId());
        assertEquals(program.getCategoryMemberships().size(), 1);
        assertEquals(program.getCategoryMemberships().iterator().next().getCategory().getId(), category.getId());
        assertEquals(category.getCategoryMemberships().size(), 1);
        assertEquals(category.getCategoryMemberships().iterator().next().getComponent().getId(), program.getId());
    }
    @Test(expected=CannotBeMemberOfCategoryIfNotMemberOfPortfolioException.class)
    public void aNewCategoryMembershipShouldBeForbidenToCreateForPortfolioWithoutParent() throws CannotBeMemberOfCategoryIfNotMemberOfPortfolioException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        CategoryMembership categoryMembership = categorizationService.createCategoryMembership(portfolio, category);
    }
    @Test
    public void aCategoryMembershipShouldBeDeleted() throws InvalidParentComponentException, CannotBeMemberOfCategoryIfNotMemberOfPortfolioException {
        InventoryServiceInterface inventoryService = new InventoryService();
        CategorizationServiceInterface categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        CategoryMembership categoryMembership = categorizationService.createCategoryMembership(program, category);
        categorizationService.deleteCategoryMembership(categoryMembership);

        assertEquals(program.getCategoryMemberships().size(), 0);
        assertEquals(category.getCategoryMemberships().size(), 0);

        program = inventoryService.getProgram(program.getId());
        category = categorizationService.getCategory(category.getId());

        assertEquals(program.getCategoryMemberships().size(), 0);
        assertEquals(category.getCategoryMemberships().size(), 0);
    }
}
