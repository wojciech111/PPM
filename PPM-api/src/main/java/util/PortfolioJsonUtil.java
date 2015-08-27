package util; /**
 * Created by Wojciech on 2015-04-17.
 */
import com.google.gson.*;
import spark.ResponseTransformer;
import util.exclusionstrategy.PortfolioTreeExclusionStrategy;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PortfolioJsonUtil {

    public static String toJson(Object object) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .setDateFormat("dd/MM/yyyy")
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setExclusionStrategies(new PortfolioTreeExclusionStrategy())
                .serializeNulls()
                .setPrettyPrinting()
                .create();
        return gson.toJson(object);
    }

    public static ResponseTransformer json() {
        return PortfolioJsonUtil::toJson;
    }
}
