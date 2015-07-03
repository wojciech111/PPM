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
public class CategorizationService {
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

    public NumericScoringCriterion createNumericScoringCriterion(String code, String name, String description, SuperiorityStrategy bestIs) {
        NumericScoringCriterion numericScoringCriterion = new NumericScoringCriterion(code,name,description,bestIs);

        numericScoringCriterion = (NumericScoringCriterion) ScoringCriterionDAO.save(numericScoringCriterion);

        return numericScoringCriterion;
    }
    public TextScoringCriterion createTextScoringCriterion(String code, String name, String description, String question) {
        TextScoringCriterion textScoringCriterion = new TextScoringCriterion(code,name,description, question);

        textScoringCriterion = (TextScoringCriterion) ScoringCriterionDAO.save(textScoringCriterion);

        return textScoringCriterion;
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

    public NumericScore createNumericScore(Component component, NumericScoringCriterion scoringCriterion, BigDecimal score, String motivation) throws CannotScoreIfNotMemberOfPortfolioException {
        if (component.getParent() == null)
            throw new CannotScoreIfNotMemberOfPortfolioException("Portfolio need parent portfolio to assign score");
        NumericScore numericScore = new NumericScore(component, scoringCriterion, score, motivation);
        component.getScores().add(numericScore);
        numericScore = (NumericScore) ScoreDAO.save(numericScore);
        return numericScore;
    }
    public TextScore createTextScore(Component component, TextScoringCriterion scoringCriterion, String answer, String motivation) throws CannotScoreIfNotMemberOfPortfolioException {
        if (component.getParent() == null)
            throw new CannotScoreIfNotMemberOfPortfolioException("Portfolio need parent portfolio to assign score");
        TextScore textScore = new TextScore(component, scoringCriterion, answer, motivation);
        component.getScores().add(textScore);
        textScore = (TextScore) ScoreDAO.save(textScore);
        return textScore;
    }
    public Score updateScore(Score score) {
        return ScoreDAO.update(score);
    }

    public void deleteScore(Score score){
        ScoreDAO.delete(score);
        score.getComponent().getScores().remove(score);
    }
}
