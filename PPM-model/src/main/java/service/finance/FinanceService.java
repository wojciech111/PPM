package service.finance;

import dao.finance.BudgetDAO;
import dao.finance.CostDAO;
import model.finance.Budget;
import model.finance.Cost;
import model.inventory.Component;
import model.inventory.Portfolio;
import model.organization.Organization;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Wojciech on 2015-09-06.
 */
public class FinanceService {
    //COST
    //@Override
    public Cost createCost(String name, BigDecimal value, Component component, String description, Integer dayOfOccurence) {
        Cost cost = new Cost( name,  value,  component,  description,  dayOfOccurence);

        cost = CostDAO.save(cost);

        return cost;
    }

    //@Override
    public Cost getCost(long id) {
        return CostDAO.getById(id);
    }

   // @Override
    public Cost updateCost(Cost cost){
        return CostDAO.update(cost);
    }

    //@Override
    public void deleteCost(Cost cost) {
        CostDAO.delete(cost);
    }

    public Budget createBudget(Portfolio portfolio, Organization organization, Date fromDate, Date toDate, BigDecimal amountOfMoney, String name, String description) {
        Budget budget = new Budget( portfolio,  organization,  fromDate,  toDate,  amountOfMoney,name,description);
        portfolio.getBudgets().add(budget);
        budget = BudgetDAO.save(budget);

        return budget;
    }
}
