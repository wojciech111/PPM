package service.categorization;

import dao.categorization.AreaOfFocusDAO;
import dao.categorization.CategoryDAO;
import dao.inventory.PortfolioDAO;
import model.categorization.AreaOfFocus;
import model.categorization.Category;
import model.inventory.Portfolio;
import org.w3c.dom.ranges.RangeException;
import util.exception.OutOfRangeException;

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
    public AreaOfFocus setAreaOfFocus(Portfolio portfolio, Category category, Short percentageOfFocus) throws OutOfRangeException {
        if (percentageOfFocus != null){
            if (percentageOfFocus < 0 || percentageOfFocus > 100)
                throw new OutOfRangeException("Percentage must be beetwen 0 and 100");
        } else {
            percentageOfFocus=0;
        }
        AreaOfFocus areaOfFocus = new AreaOfFocus(portfolio,category,percentageOfFocus);
        portfolio.getAreasOfFocus().add(areaOfFocus);
        areaOfFocus = AreaOfFocusDAO.save(areaOfFocus);
        PortfolioDAO.update(portfolio);
        return areaOfFocus;
    }

    /*public Portfolio getAreasOfFocusWithCategories(Portfolio portfolio) {
        AreaOfFocusDAO.getAll(portfolio);

        return portfolio;
    }*/
}
