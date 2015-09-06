package service.finance;

import dao.finance.CostDAO;
import model.finance.Cost;
import model.inventory.Component;

import java.math.BigDecimal;

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
}
