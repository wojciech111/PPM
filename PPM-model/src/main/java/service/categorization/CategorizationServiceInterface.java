package service.categorization;

import model.categorization.*;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Component;
import model.inventory.Portfolio;
import model.inventory.Program;
import util.exception.CannotBeMemberOfCategoryIfNotMemberOfPortfolioException;
import util.exception.CannotScoreIfNotMemberOfPortfolioException;
import util.exception.OutOfRangeException;

import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-07-05.
 */
public interface CategorizationServiceInterface {
    //CATEGORY
    Category createCategory(String code, String name, String description);

    Category getCategory(long id);

    Category updateCategory(Category category);

    void deleteCategory(Category category);

    //AREA OF FOCUS
    AreaOfFocus createAreaOfFocus(Portfolio portfolio, Category category, Short percentageOfFocus) throws OutOfRangeException;

    AreaOfFocus updateAreaOfFocus(AreaOfFocus areaOfFocus) throws OutOfRangeException;

    void deleteAreaOfFocus(AreaOfFocus areaOfFocus);

    //CATEGORY MEMBERSHIP
    CategoryMembership createCategoryMembership(Component component, Category category, Short percentageOfSupport,
                                                BigDecimal overallScore, Short rankInCategory) throws CannotBeMemberOfCategoryIfNotMemberOfPortfolioException;

    CategoryMembership createCategoryMembership(Component component, Category category) throws CannotBeMemberOfCategoryIfNotMemberOfPortfolioException;

    void deleteCategoryMembership(CategoryMembership categoryMembership);

    //SCORING CRITERION
    public ScoringCriterion createScoringCriterion(String code, String name,
                                                   String description, String question,
                                                   SuperiorityStrategy bestIs,
                                                   BigDecimal minScore, BigDecimal maxScore,
                                                   Portfolio portfolio);

    ScoringCriterion createScoringCriterion(String code, String name, String description, SuperiorityStrategy bestIs);

    ScoringCriterion getScoringCriterion(long id);

    ScoringCriterion updateScoringCriterion(ScoringCriterion scoringCriterion);

    void deleteScoringCriterion(ScoringCriterion scoringCriterion);

    //CATEGORY EVALUATION

    CategoryEvaluation createCategoryEvaluation(ScoringCriterion scoringCriterion, Category category, String isKey, Short weight);

    CategoryEvaluation createCategoryEvaluation(ScoringCriterion scoringCriterion, Category category);

    CategoryEvaluation updateCategoryEvaluation(CategoryEvaluation categoryEvaluation);

    void deleteCategoryEvaluation(CategoryEvaluation categoryEvaluation);

    //SCORE

    Score createScore(Component component, ScoringCriterion scoringCriterion, BigDecimal score, String motivation) throws CannotScoreIfNotMemberOfPortfolioException;

    Score updateScore(Score score);

    void deleteScore(Score score);

}
