package util; /**
 * Created by Wojciech on 2015-04-17.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import spark.ResponseTransformer;
import util.exclusionstrategy.PortfolioTreeExclusionStrategy;
import util.exclusionstrategy.UserTreeExclusionStrategy;

public class UserJsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.setExclusionStrategies(new UserTreeExclusionStrategy()).serializeNulls().create();
        return gson.toJson(object);
    }

    public static ResponseTransformer json() {
        return UserJsonUtil::toJson;
    }
}
