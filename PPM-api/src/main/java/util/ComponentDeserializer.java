package util;

import com.google.gson.*;
import model.inventory.Component;
import model.inventory.enums.ComponentType;

import java.lang.reflect.Type;


/**
 * Created by Wojciech on 2015-08-12.
 */
public class ComponentDeserializer implements JsonDeserializer<Component> {

    @Override
    public Component deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String kind = object.get("componentType").getAsString();
        Class<?> clazz = ComponentType.forName(kind).clazz;
        Component result = null;

        try {
            result = context.deserialize(jsonElement,clazz);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
