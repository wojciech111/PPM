package util.exclusionstrategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import model.inventory.Portfolio;
import util.annotation.PortfolioTree;

/**
 * Created by Wojciech on 2015-08-04.
 */
public class PortfolioTreeExclusionStrategy implements ExclusionStrategy {
    
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(PortfolioTree.class) == null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
