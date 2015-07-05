package util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wojciech on 2015-05-15.
 */
public class TestResponse {
    public final String body;
    public final int status;

    public TestResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public Map<String,String> json() {
        return new Gson().fromJson(body, HashMap.class);
    }
}
