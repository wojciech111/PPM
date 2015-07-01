package service.categorization;

import dao.categorization.AreaOfFocusDAO;
import dao.categorization.CategoryDAO;
import dao.categorization.CategoryMembershipDAO;
import dao.categorization.ScoringCriterionDAO;
import dao.inventory.PortfolioDAO;
import model.categorization.*;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Component;
import model.inventory.Portfolio;
import org.w3c.dom.ranges.RangeException;
import util.exception.OutOfRangeException;
import util.exception.PortfolioWithoutParentCannotBeMemberOfCategoryException;

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
    public CategoryMembership createCategoryMembership(Component component, Category category) throws PortfolioWithoutParentCannotBeMemberOfCategoryException {
        if (component.getParent() == null)
            throw new PortfolioWithoutParentCannotBeMemberOfCategoryException("Portfolio without parent cannot be member of category");
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


}
