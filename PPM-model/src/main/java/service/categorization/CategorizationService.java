package service.categorization;

import dao.categorization.*;
import model.categorization.*;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Component;
import model.inventory.Portfolio;
import util.exception.CannotScoreIfNotMemberOfPortfolioException;
import util.exception.OutOfRangeException;
import util.exception.CannotBeMemberOfCategoryIfNotMemberOfPortfolioException;

import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CategorizationService implements CategorizationServiceInterface {
    //CATEGORY
    public Category createCategory(String code, String name, String description) {
        Category category = new Category(code,name,description);

        category = CategoryDAO.save(category);

        return category;
    }

    public Category getCategory(long id) {
        return CategoryDAO.getById(id);
    }

    public Category updateCategory(Category category){
        return CategoryDAO.update(category);
    }

    public void deleteCategory(Category category) {
        CategoryDAO.delete(category);
    }

    //AREA OF FOCUS
    public AreaOfFocus createAreaOfFocus(Portfolio portfolio, Category category, Short percentageOfFocus) throws OutOfRangeException {
        if (percentageOfFocus != null){
            if (percentageOfFocus < 0 || percentageOfFocus > 100)
                throw new OutOfRangeException("Percentage must be beetwen 0 and 100");
        } else {
            percentageOfFocus=0;
        }
        AreaOfFocus areaOfFocus = new AreaOfFocus(portfolio,category,percentageOfFocus);
        portfolio.getAreasOfFocus().add(areaOfFocus);
        areaOfFocus = AreaOfFocusDAO.save(areaOfFocus);
        //PortfolioDAO.update(portfolio);
        return areaOfFocus;
    }
    public AreaOfFocus updateAreaOfFocus(AreaOfFocus areaOfFocus) throws OutOfRangeException {
        if (areaOfFocus.getPercentageOfFocus() != null){
            if (areaOfFocus.getPercentageOfFocus() < 0 || areaOfFocus.getPercentageOfFocus() > 100)
                throw new OutOfRangeException("Percentage must be beetwen 0 and 100");
        } else {
            areaOfFocus.setPercentageOfFocus((short)0);
        }
        areaOfFocus=AreaOfFocusDAO.update(areaOfFocus);
        return areaOfFocus;
    }

    public void deleteAreaOfFocus(AreaOfFocus areaOfFocus) {
        AreaOfFocusDAO.delete(areaOfFocus);
        areaOfFocus.getPortfolio().getAreasOfFocus().remove(areaOfFocus);
    }

    //CATEGORY MEMBERSHIP
    public CategoryMembership createCategoryMembership(Component component, Category category, Short percentageOfSupport, BigDecimal overallScore, Short rankInCategory) throws CannotBeMemberOfCategoryIfNotMemberOfPortfolioException {
        if (component.getParent() == null)
            throw new CannotBeMemberOfCategoryIfNotMemberOfPortfolioException("Portfolio without parent cannot be member of category");
        CategoryMembership categoryMembership = new CategoryMembership(component,category, percentageOfSupport,overallScore, rankInCategory);
        component.getCategoryMemberships().add(categoryMembership);
        category.getCategoryMemberships().add(categoryMembership);
        categoryMembership = CategoryMembershipDAO.save(categoryMembership);
        return categoryMembership;
    }

    public CategoryMembership createCategoryMembership(Component component, Category category) throws CannotBeMemberOfCategoryIfNotMemberOfPortfolioException {
        if (component.getParent() == null)
            throw new CannotBeMemberOfCategoryIfNotMemberOfPortfolioException("Portfolio without parent cannot be member of category");
        CategoryMembership categoryMembership = new CategoryMembership(component,category);
        component.getCategoryMemberships().add(categoryMembership);
        category.getCategoryMemberships().add(categoryMembership);
        categoryMembership = CategoryMembershipDAO.save(categoryMembership);
        return categoryMembership;
    }


    public void deleteCategoryMembership(CategoryMembership categoryMembership) {
        CategoryMembershipDAO.delete(categoryMembership);
        categoryMembership.getComponent().getCategoryMemberships().remove(categoryMembership);
        categoryMembership.getCategory().getCategoryMemberships().remove(categoryMembership);
    }

    //SCORING CRITERION

    public ScoringCriterion createScoringCriterion(String code, String name,
                                                   String description, String question,
                                                   SuperiorityStrategy bestIs,
                                                   BigDecimal minScore, BigDecimal maxScore,
                                                   Portfolio portfolio) {
        ScoringCriterion numericScoringCriterion = new ScoringCriterion( code,  name,
                 description,  question,bestIs,
                 minScore,  maxScore,portfolio);

        numericScoringCriterion = (ScoringCriterion) ScoringCriterionDAO.save(numericScoringCriterion);

        return numericScoringCriterion;
    }

    public ScoringCriterion createScoringCriterion(String code, String name, String description, SuperiorityStrategy bestIs) {
        ScoringCriterion numericScoringCriterion = new ScoringCriterion(code,name,description,bestIs);

        numericScoringCriterion = (ScoringCriterion) ScoringCriterionDAO.save(numericScoringCriterion);

        return numericScoringCriterion;
    }

    public ScoringCriterion getScoringCriterion(long id) {
        return ScoringCriterionDAO.getById(id);
    }

    public ScoringCriterion updateScoringCriterion(ScoringCriterion scoringCriterion){
        return ScoringCriterionDAO.update(scoringCriterion);
    }

    public void deleteScoringCriterion(ScoringCriterion scoringCriterion) {
        ScoringCriterionDAO.delete(scoringCriterion);
    }

    //CATEGORY EVALUATION

    public CategoryEvaluation createCategoryEvaluation(ScoringCriterion scoringCriterion, Category category, String isKey, Short weight) {
        CategoryEvaluation categoryEvaluation = new CategoryEvaluation(scoringCriterion,category,  isKey,  weight);
        category.getCategoryEvaluations().add(categoryEvaluation);
        categoryEvaluation = CategoryEvaluationDAO.save(categoryEvaluation);
        return categoryEvaluation;
    }
    public CategoryEvaluation createCategoryEvaluation(ScoringCriterion scoringCriterion, Category category) {
        CategoryEvaluation categoryEvaluation = new CategoryEvaluation(scoringCriterion,category);
        category.getCategoryEvaluations().add(categoryEvaluation);
        categoryEvaluation = CategoryEvaluationDAO.save(categoryEvaluation);
        return categoryEvaluation;
    }
    public CategoryEvaluation updateCategoryEvaluation(CategoryEvaluation categoryEvaluation){
        return CategoryEvaluationDAO.update(categoryEvaluation);
    }
    public void deleteCategoryEvaluation(CategoryEvaluation categoryEvaluation){
        CategoryEvaluationDAO.delete(categoryEvaluation);
        categoryEvaluation.getCategory().getCategoryEvaluations().remove(categoryEvaluation);
    }

    //SCORE

    public Score createScore(Component component, ScoringCriterion scoringCriterion, BigDecimal score, String motivation) throws CannotScoreIfNotMemberOfPortfolioException {
        if (component.getParent() == null)
            throw new CannotScoreIfNotMemberOfPortfolioException("Portfolio need parent portfolio to assign score");
        Score theScore = new Score(component, scoringCriterion, score, motivation);
        component.getScores().add(theScore);
        theScore =  ScoreDAO.save(theScore);
        return theScore;
    }

    public Score updateScore(Score score) {
        return ScoreDAO.update(score);
    }

    public void deleteScore(Score score){
        ScoreDAO.delete(score);
        score.getComponent().getScores().remove(score);
    }
}
