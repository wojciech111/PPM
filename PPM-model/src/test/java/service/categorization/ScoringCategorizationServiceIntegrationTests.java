package service.categorization;

import model.categorization.*;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Portfolio;
import model.inventory.Program;
import org.junit.Test;
import service.inventory.InventoryService;
import util.exception.CannotScoreIfNotMemberOfPortfolioException;
import util.exception.InvalidParentComponentException;

import static org.junit.Assert.*;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class ScoringCategorizationServiceIntegrationTests {
    /*@After
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
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }*/

    //NUMERIC SCORING CRITERION

    @Test
    public void aNewNumericScoringCriterionShouldBeCreated(){
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);
        assertTrue(numericScoringCriterion.getCode().startsWith("ROI"));
        assertEquals("Return on investment", numericScoringCriterion.getName());
        assertEquals("Piêkny opis tego czym ten wskaŸnik jest", numericScoringCriterion.getDescription());
        assertEquals(SuperiorityStrategy.MAX, numericScoringCriterion.getBestIs());
        assertNotEquals(numericScoringCriterion.getId(), 0);
    }
    @Test
    public void numericScoringCriterionShouldBeTakenById() {
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);
        ScoringCriterion numericScoringCriterionById = categorizationService.getScoringCriterion(numericScoringCriterion.getId());
        assertTrue(numericScoringCriterionById.getCode().startsWith(numericScoringCriterion.getCode()));
        assertEquals(numericScoringCriterionById.getName(), numericScoringCriterion.getName());
        assertEquals(numericScoringCriterionById.getDescription(), numericScoringCriterion.getDescription());
        assertEquals(numericScoringCriterionById.getId(), numericScoringCriterion.getId());
        assertEquals(((NumericScoringCriterion)numericScoringCriterionById).getBestIs(), numericScoringCriterion.getBestIs());

    }
    @Test
    public void numericScoringCriterionShouldBeUpdated() {
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);
        numericScoringCriterion.setCode("PFFF");
        numericScoringCriterion.setName("NOWY ROI");
        numericScoringCriterion = (NumericScoringCriterion) categorizationService.updateScoringCriterion(numericScoringCriterion);
        ScoringCriterion numericScoringCriterionById = categorizationService.getScoringCriterion(numericScoringCriterion.getId());
        assertTrue(numericScoringCriterionById.getCode().startsWith("PFFF"));
        assertEquals(numericScoringCriterionById.getName(), "NOWY ROI");
        assertEquals(numericScoringCriterionById.getDescription(), numericScoringCriterion.getDescription());
        assertEquals(numericScoringCriterionById.getId(), numericScoringCriterion.getId());
        assertEquals(((NumericScoringCriterion)numericScoringCriterionById).getBestIs(), numericScoringCriterion.getBestIs());
    }
    @Test
    public void numericScoringCriterionShouldBeDeleted(){
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);
        ScoringCriterion numericScoringCriterionById = categorizationService.getScoringCriterion(numericScoringCriterion.getId());
        categorizationService.deleteScoringCriterion(numericScoringCriterion);
        ScoringCriterion numericScoringCriterionByIdDeleted = categorizationService.getScoringCriterion(numericScoringCriterion.getId());
        assertNotNull(numericScoringCriterionById);
        assertNull(numericScoringCriterionByIdDeleted);
    }

    //TEXT SCORING CRITERION

    @Test
    public void aNewTextScoringCriterionShouldBeCreated(){
        CategorizationService categorizationService = new CategorizationService();
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", "Pytanie, które jest zadawane do oceny?");
        assertTrue(textScoringCriterion.getCode().startsWith("ROI"));
        assertEquals("Return on investment", textScoringCriterion.getName());
        assertEquals("Piêkny opis tego czym ten wskaŸnik jest", textScoringCriterion.getDescription());
        assertEquals("Pytanie, które jest zadawane do oceny?", textScoringCriterion.getQuestion());
        assertNotEquals(textScoringCriterion.getId(), 0);
    }
    @Test
    public void textScoringCriterionShouldBeTakenById() {
        CategorizationService categorizationService = new CategorizationService();
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", "Pytanie, które jest zadawane do oceny?");
        ScoringCriterion textScoringCriterionById = categorizationService.getScoringCriterion(textScoringCriterion.getId());
        assertTrue(textScoringCriterionById.getCode().startsWith(textScoringCriterion.getCode()));
        assertEquals(textScoringCriterionById.getName(), textScoringCriterion.getName());
        assertEquals(textScoringCriterionById.getDescription(), textScoringCriterion.getDescription());
        assertEquals(textScoringCriterionById.getId(), textScoringCriterion.getId());
        assertEquals(((TextScoringCriterion)textScoringCriterionById).getQuestion(), textScoringCriterion.getQuestion());

    }
    @Test
    public void textScoringCriterionShouldBeUpdated() {
        CategorizationService categorizationService = new CategorizationService();
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", "Pytanie, które jest zadawane do oceny?");
        textScoringCriterion.setCode("PFFF");
        textScoringCriterion.setName("NOWY ROI");
        textScoringCriterion = (TextScoringCriterion) categorizationService.updateScoringCriterion(textScoringCriterion);
        ScoringCriterion textScoringCriterionById = categorizationService.getScoringCriterion(textScoringCriterion.getId());
        assertTrue(textScoringCriterionById.getCode().startsWith("PFFF"));
        assertEquals(textScoringCriterionById.getName(), "NOWY ROI");
        assertEquals(textScoringCriterionById.getDescription(), textScoringCriterion.getDescription());
        assertEquals(textScoringCriterionById.getId(), textScoringCriterion.getId());
        assertEquals(((TextScoringCriterion)textScoringCriterionById).getQuestion(), textScoringCriterion.getQuestion());
    }
    @Test
    public void textScoringCriterionShouldBeDeleted(){
        CategorizationService categorizationService = new CategorizationService();
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", "Pytanie, które jest zadawane do oceny?");
        ScoringCriterion textScoringCriterionById = categorizationService.getScoringCriterion(textScoringCriterion.getId());
        categorizationService.deleteScoringCriterion(textScoringCriterion);
        ScoringCriterion textScoringCriterionByIdDeleted = categorizationService.getScoringCriterion(textScoringCriterion.getId());
        assertNotNull(textScoringCriterionById);
        assertNull(textScoringCriterionByIdDeleted);
    }

    //CATEGORY EVALUATION
        //Numeric
    @Test
    public void numericScoringCriterionShouldBeAddedToCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(numericScoringCriterion, category);

        assertEquals(categoryEvaluation.getCategory().getId(),category.getId());
        assertEquals(categoryEvaluation.getScoringCriterion().getId(), numericScoringCriterion.getId());
        assertEquals(category.getCategoryEvaluations().size(), 1);
        assertEquals(category.getCategoryEvaluations().iterator().next().getScoringCriterion().getId(), numericScoringCriterion.getId());
    }

    @Test
    public void numericScoringCriterionShouldBeDeletedFromCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(numericScoringCriterion, category);

        category = categorizationService.getCategory(category.getId());
        categorizationService.deleteCategoryEvaluation(category.getCategoryEvaluations().iterator().next());

        assertEquals(category.getCategoryMemberships().size(), 0);

        category = categorizationService.getCategory(category.getId());

        assertEquals(category.getCategoryMemberships().size(), 0);
    }

    //CATEGORY EVALUATION
        //Text
    @Test
    public void textScoringCriterionShouldBeAddedToCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", "Pytanie, które jest zadawane do oceny?");
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(textScoringCriterion, category);


        assertEquals(categoryEvaluation.getCategory().getId(),category.getId());
        assertEquals(categoryEvaluation.getScoringCriterion().getId(), textScoringCriterion.getId());
        assertEquals(category.getCategoryEvaluations().size(), 1);
        assertEquals(category.getCategoryEvaluations().iterator().next().getScoringCriterion().getId(), textScoringCriterion.getId());
    }

    @Test
    public void textScoringCriterionShouldBeDeletedFromCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", "Pytanie, które jest zadawane do oceny?");
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(textScoringCriterion, category);

        assertEquals(category.getCategoryEvaluations().size(), 1);

        categorizationService.deleteCategoryEvaluation(categoryEvaluation);
        category = categorizationService.getCategory(category.getId());

        assertEquals(category.getCategoryEvaluations().size(), 0);

    }

    //NUMERIC SCORE
/*
    @Test
    public void aNewNumericScoreForComponentShouldBeCreated() throws InvalidParentComponentException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Piêkny opis tego czym ten wskaŸnik jest", SuperiorityStrategy.MAX);

    }


    @Test(expected = CannotScoreIfNotMemberOfPortfolioException.class)
    public void aNumericScoreShouldBeForbidenToCreateForPortfolioWithoutParent(){

    }
    @Test
    public void numericScoreForComponentShouldBeUpdated(){

    }
    @Test
    public void numericScoreForComponentShouldBeDeleted(){

    }
    */
}
