package util.exclusionstrategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import util.annotation.PortfolioTree;
import util.annotation.UserTree;

/**
 * Created by Wojciech on 2015-08-04.
 */
public class UserTreeExclusionStrategy implements ExclusionStrategy {
    
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(UserTree.class) == null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
