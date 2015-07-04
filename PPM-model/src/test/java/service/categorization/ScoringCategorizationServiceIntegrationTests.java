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
    public void aNewScoringCriterionShouldBeCreated(){
        CategorizationService categorizationService = new CategorizationService();
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        assertTrue(scoringCriterion.getCode().startsWith("ROI"));
        assertEquals("Return on investment", scoringCriterion.getName());
        assertEquals("Pi�kny opis tego czym ten wska�nik jest", scoringCriterion.getDescription());
        assertEquals(SuperiorityStrategy.MAX, scoringCriterion.getBestIs());
        assertNotEquals(scoringCriterion.getId(), 0);
    }
    @Test
    public void scoringCriterionShouldBeTakenById() {
        CategorizationService categorizationService = new CategorizationService();
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        ScoringCriterion scoringCriterionById = categorizationService.getScoringCriterion(scoringCriterion.getId());
        assertTrue(scoringCriterionById.getCode().startsWith(scoringCriterion.getCode()));
        assertEquals(scoringCriterionById.getName(), scoringCriterion.getName());
        assertEquals(scoringCriterionById.getDescription(), scoringCriterion.getDescription());
        assertEquals(scoringCriterionById.getId(), scoringCriterion.getId());
        assertEquals((scoringCriterionById).getBestIs(), scoringCriterion.getBestIs());

    }
    @Test
    public void scoringCriterionShouldBeUpdated() {
        CategorizationService categorizationService = new CategorizationService();
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        scoringCriterion.setCode("PFFF");
        scoringCriterion.setName("NOWY ROI");
        scoringCriterion = (ScoringCriterion) categorizationService.updateScoringCriterion(scoringCriterion);
        ScoringCriterion scoringCriterionById = categorizationService.getScoringCriterion(scoringCriterion.getId());
        assertTrue(scoringCriterionById.getCode().startsWith("PFFF"));
        assertEquals(scoringCriterionById.getName(), "NOWY ROI");
        assertEquals(scoringCriterionById.getDescription(), scoringCriterion.getDescription());
        assertEquals(scoringCriterionById.getId(), scoringCriterion.getId());
        assertEquals(( scoringCriterionById).getBestIs(), scoringCriterion.getBestIs());
    }
    @Test
    public void scoringCriterionShouldBeDeleted(){
        CategorizationService categorizationService = new CategorizationService();
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        ScoringCriterion scoringCriterionById = categorizationService.getScoringCriterion(scoringCriterion.getId());
        categorizationService.deleteScoringCriterion(scoringCriterion);
        ScoringCriterion scoringCriterionByIdDeleted = categorizationService.getScoringCriterion(scoringCriterion.getId());
        assertNotNull(scoringCriterionById);
        assertNull(scoringCriterionByIdDeleted);
    }

    //CATEGORY EVALUATION
    @Test
    public void scoringCriterionShouldBeAddedToCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj�cy");
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(scoringCriterion, category);

        assertEquals(categoryEvaluation.getCategory().getId(), category.getId());
        assertEquals(categoryEvaluation.getScoringCriterion().getId(), scoringCriterion.getId());
        assertEquals(category.getCategoryEvaluations().size(), 1);
        assertEquals(category.getCategoryEvaluations().iterator().next().getScoringCriterion().getId(), scoringCriterion.getId());
    }

    @Test
    public void scoringCriterionShouldBeDeletedFromCategory(){
        CategorizationService categorizationService = new CategorizationService();
        Category category = categorizationService.createCategory("CA1", "Kategoria wyborna", "opis kategorii ktory jest niezwykle wyczerpuj�cy");
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);
        CategoryEvaluation categoryEvaluation = categorizationService.createCategoryEvaluation(scoringCriterion, category);

        category = categorizationService.getCategory(category.getId());
        categorizationService.deleteCategoryEvaluation(category.getCategoryEvaluations().iterator().next());

        assertEquals(category.getCategoryMemberships().size(), 0);

        category = categorizationService.getCategory(category.getId());

        assertEquals(category.getCategoryMemberships().size(), 0);
    }

    //NUMERIC SCORE

    @Test
    public void aNewScoreForComponentShouldBeCreated() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        Score theScore = categorizationService.createScore(program, scoringCriterion, new BigDecimal(456.232), "To jes dla tego, ze jest");
        program = inventoryService.getProgram(program.getId());
        BigDecimal number = new BigDecimal(456.23).setScale(2, BigDecimal.ROUND_HALF_UP);
        assertEquals(theScore.getScore(), number);
        assertEquals(theScore.getComponent().getId(),program.getId());
        assertEquals(theScore.getScoringCriterion().getId(), scoringCriterion.getId());
        assertEquals(program.getScores().size(), 1);
        assertEquals(program.getScores().iterator().next().getScoringCriterion().getId(), scoringCriterion.getId());

    }

    @Test(expected = CannotScoreIfNotMemberOfPortfolioException.class)
    public void aScoreShouldBeForbidenToCreateForPortfolioWithoutParent() throws CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        Score theScore = categorizationService.createScore(portfolio, scoringCriterion, new BigDecimal(456.23), "To jes dla tego, ze jest");

    }

    @Test
    public void theScoreForComponentShouldBeUpdated() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        Score theScore = categorizationService.createScore(program, scoringCriterion, new BigDecimal(456.23), "To jest dla tego, ze jest");
        BigDecimal number = new BigDecimal(555.33).setScale(2, BigDecimal.ROUND_HALF_UP);
        theScore.setScore(number);
        theScore =  categorizationService.updateScore(theScore);

        program = inventoryService.getProgram(program.getId());
        theScore =  program.getScores().iterator().next();

        assertEquals(theScore.getScore(), number);

    }
    @Test
    public void theScoreForComponentShouldBeDeleted() throws InvalidParentComponentException, CannotScoreIfNotMemberOfPortfolioException {
        InventoryService inventoryService = new InventoryService();
        CategorizationService categorizationService = new CategorizationService();
        Portfolio portfolio = inventoryService.createPortfolio("PF1", "GrassHost", "customer jakis", "Opis Opisik", null);
        Program program = inventoryService.createProgram("P22", "Programmmmm", "customer jakis", "Opis Opisik", portfolio);
        ScoringCriterion scoringCriterion = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi�kny opis tego czym ten wska�nik jest", SuperiorityStrategy.MAX);

        Score theScore = categorizationService.createScore(program, scoringCriterion, new BigDecimal(456.23), "To jest dla tego, ze jest");

        assertEquals(program.getScores().size(), 1);

        categorizationService.deleteScore(theScore);
        program = inventoryService.getProgram(program.getId());

        assertEquals(program.getScores().size(), 0);
    }



}
