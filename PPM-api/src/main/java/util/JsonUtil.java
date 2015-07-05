package util; /**
 * Created by Wojciech on 2015-04-17.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import spark.ResponseTransformer;

public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
        return gson.toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
