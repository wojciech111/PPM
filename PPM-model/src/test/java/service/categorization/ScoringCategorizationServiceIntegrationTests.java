package service.categorization;

import model.categorization.*;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Portfolio;
import model.inventory.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.inventory.InventoryService;
import util.HibernateUtil;
import util.exception.CannotScoreIfNotMemberOfPortfolioException;
import util.exception.InvalidParentComponentException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class ScoringCategorizationServiceIntegrationTests {
    @Before
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

    }

    //NUMERIC SCORING CRITERION

    @Test
    public void aNewNumericScoringCriterionShouldBeCreated(){
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        assertTrue(numericScoringCriterion.getCode().startsWith("ROI"));
        assertEquals("Return on investment", numericScoringCriterion.getName());
        assertEquals("Pi�kny opis tego czym ten wska�nik jest", numericScoringCriterion.getDescription());
        assertEquals(SuperiorityStrategy.MAX, numericScoringCriterion.getBestIs());
        assertNotEquals(numericScoringCriterion.getId(), 0);
    }
    @Test
    public void numericScoringCriterionShouldBeTakenById() {
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
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
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        numericScoringCriterion.setCode("PFFF");
        numericScoringCriterion.setName("NOWY ROI");
        numericScoringCriterion = (NumericScoringCriterion) categorizationService.updateScoringCriterion(numericScoringCriterion);
        ScoringCriterion numericScoringCriterionById = categorizationService.getScoringCriterion(numericScoringCriterion.getId());
        assertTrue(numericScoringCriterionById.getCode().startsWith("PFFF"));
        assertEquals(numericScoringCriterionById.getName(), "NOWY ROI");
        assertEquals(numericScoringCriterionById.getDescription(), numericScoringCriterion.getDescription());
        assertEquals(numericScoringCriterionById.getId(), numericScoringCriterion.getId());
        assertEquals(((NumericScoringCriterion) numericScoringCriterionById).getBestIs(), numericScoringCriterion.getBestIs());
    }
    @Test
    public void numericScoringCriterionShouldBeDeleted(){
        CategorizationService categorizationService = new CategorizationService();
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
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
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie, kt�re jest zadawane do oceny?");
        assertTrue(textScoringCriterion.getCode().startsWith("ROI"));
        assertEquals("Return on investment", textScoringCriterion.getName());
        assertEquals("Pi�kny opis tego czym ten wska�nik jest", textScoringCriterion.getDescription());
        assertEquals("Pytanie, kt�re jest zadawane do oceny?", textScoringCriterion.getQuestion());
        assertNotEquals(textScoringCriterion.getId(), 0);
    }
    @Test
    public void textScoringCriterionShouldBeTakenById() {
        CategorizationService categorizationService = new CategorizationService();
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie, kt�re jest zadawane do oceny?");
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
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie, kt�re jest zadawane do oceny?");
        textScoringCriterion.setCode("PFFF");
        textScoringCriterion.setName("NOWY ROI");
        textScoringCriterion = (TextScoringCriterion) categorizationService.updateScoringCriterion(textScoringCriterion);
        ScoringCriterion textScoringCriterionById = categorizationService.getScoringCriterion(textScoringCriterion.getId());
        assertTrue(textScoringCriterionById.getCode().startsWith("PFFF"));
        assertEquals(textScoringCriterionById.getName(), "NOWY ROI");
        assertEquals(textScoringCriterionById.getDescription(), textScoringCriterion.getDescription());
        assertEquals(textScoringCriterionById.getId(), textScoringCriterion.getId());
        assertEquals(((TextScoringCriterion) textScoringCriterionById).getQuestion(), textScoringCriterion.getQuestion());
    }
    @Test
    public void textScoringCriterionShouldBeDeleted(){
        CategorizationService categorizationService = new CategorizationService();
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie, kt�re jest zadawane do oceny?");
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
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj�cy");
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(numericScoringCriterion, category);

        assertEquals(categoryEvaluation.getCategory().getId(), category.getId());
        assertEquals(categoryEvaluation.getScoringCriterion().getId(), numericScoringCriterion.getId());
        assertEquals(category.getCategoryEvaluations().size(), 1);
        assertEquals(category.getCategoryEvaluations().iterator().next().getScoringCriterion().getId(), numericScoringCriterion.getId());
    }

    @Test
    public void numericScoringCriterionShouldBeDeletedFromCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj�cy");
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
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
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj�cy");
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie, kt�re jest zadawane do oceny?");
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(textScoringCriterion, category);


        assertEquals(categoryEvaluation.getCategory().getId(),category.getId());
        assertEquals(categoryEvaluation.getScoringCriterion().getId(), textScoringCriterion.getId());
        assertEquals(category.getCategoryEvaluations().size(), 1);
        assertEquals(category.getCategoryEvaluations().iterator().next().getScoringCriterion().getId(), textScoringCriterion.getId());
    }

    @Test
    public void textScoringCriterionShouldBeDeletedFromCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj�cy");
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie, kt�re jest zadawane do oceny?");
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(textScoringCriterion, category);

        assertEquals(category.getCategoryEvaluations().size(), 1);

        categorizationService.deleteCategoryEvaluation(categoryEvaluation);
        category = categorizationService.getCategory(category.getId());

        assertEquals(category.getCategoryEvaluations().size(), 0);

    }

    //NUMERIC SCORE

    @Test
    public void aNewNumericScoreForComponentShouldBeCreated() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        NumericScore numericScore = categorizationService.createNumericScore(program, numericScoringCriterion, new BigDecimal(456.232), "To jes dla tego, ze jest");
        program = inventoryService.getProgram(program.getId());
        BigDecimal number = new BigDecimal(456.23).setScale(2, BigDecimal.ROUND_HALF_UP);
        assertEquals(numericScore.getScore(), number);
        assertEquals(numericScore.getComponent().getId(),program.getId());
        assertEquals(numericScore.getScoringCriterion().getId(), numericScoringCriterion.getId());
        assertEquals(program.getScores().size(), 1);
        assertEquals(program.getScores().iterator().next().getScoringCriterion().getId(), numericScoringCriterion.getId());

    }

    @Test(expected = CannotScoreIfNotMemberOfPortfolioException.class)
    public void aNumericScoreShouldBeForbidenToCreateForPortfolioWithoutParent() throws CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        NumericScore numericScore = categorizationService.createNumericScore(portfolio, numericScoringCriterion, new BigDecimal(456.23), "To jes dla tego, ze jest");

    }

    @Test
    public void numericScoreForComponentShouldBeUpdated() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        NumericScore numericScore = categorizationService.createNumericScore(program, numericScoringCriterion, new BigDecimal(456.23), "To jest dla tego, ze jest");
        BigDecimal number = new BigDecimal(555.33).setScale(2, BigDecimal.ROUND_HALF_UP);
        numericScore.setScore(number);
        numericScore = (NumericScore) categorizationService.updateScore(numericScore);

        program = inventoryService.getProgram(program.getId());
        numericScore = (NumericScore) program.getScores().iterator().next();

        assertEquals(numericScore.getScore(), number);

    }
    @Test
    public void numericScoreForComponentShouldBeDeleted() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        NumericScoringCriterion numericScoringCriterion = categorizationService.createNumericScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        NumericScore numericScore = categorizationService.createNumericScore(program, numericScoringCriterion, new BigDecimal(456.23), "To jest dla tego, ze jest");

        assertEquals(program.getScores().size(), 1);

        categorizationService.deleteScore(numericScore);
        program = inventoryService.getProgram(program.getId());

        assertEquals(program.getScores().size(), 0);
    }

    //TEXT SCORE

    @Test
    public void aNewTextScoreForComponentShouldBeCreated() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", "Pytanie konkursowe numer jeden?");

        TextScore textScore = categorizationService.createTextScore(program, textScoringCriterion, "wyj�tkowe zdolno�ci w tym zakresie", "To jes dla tego, ze jest");
        program = inventoryService.getProgram(program.getId());
        assertEquals(textScore.getAnswer(), "wyj�tkowe zdolno�ci w tym zakresie");

        assertEquals(textScore.getComponent().getId(),program.getId());
        assertEquals(textScore.getScoringCriterion().getId(), textScoringCriterion.getId());
        assertEquals(program.getScores().size(), 1);
        assertEquals(program.getScores().iterator().next().getScoringCriterion().getId(), textScoringCriterion.getId());

    }

    @Test(expected = CannotScoreIfNotMemberOfPortfolioException.class)
    public void aTextScoreShouldBeForbidenToCreateForPortfolioWithoutParent() throws CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest",  "Pytanie konkursowe numer jeden?");

        TextScore textScore = categorizationService.createTextScore(portfolio, textScoringCriterion, "wyj�tkowe zdolno�ci w tym zakresie", "To jes dla tego, ze jest");

    }

    @Test
    public void textScoreForComponentShouldBeUpdated() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest",  "Pytanie konkursowe numer jeden?");

        TextScore textScore = categorizationService.createTextScore(program, textScoringCriterion, "wyj�tkowe w tym zakresie", "To jest dla tego, ze jest");
        program = inventoryService.getProgram(program.getId());
        textScore = (TextScore) program.getScores().iterator().next();
        textScore.setAnswer("To jest je");
        textScore = (TextScore) categorizationService.updateScore(textScore);

        program = inventoryService.getProgram(program.getId());
        textScore = (TextScore) program.getScores().iterator().next();

        assertEquals(textScore.getAnswer(), "To jest jednak lepsza odpowiedz");

    }
    @Test
    public void textScoreForComponentShouldBeDeleted() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        TextScoringCriterion textScoringCriterion = categorizationService.createTextScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest",  "Pytanie konkursowe numer jeden?");

        TextScore textScore = categorizationService.createTextScore(program, textScoringCriterion, "wyj�tkowe zdolno�ci w tym zakresie", "To jest dla tego, ze jest");

        assertEquals(program.getScores().size(), 1);

        categorizationService.deleteScore(textScore);
        program = inventoryService.getProgram(program.getId());

        assertEquals(program.getScores().size(), 0);
    }


}
