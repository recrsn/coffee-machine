package coffee.machine.spec;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineSpecDeserializer implements JsonDeserializer<CoffeeMachineTestSpec> {
    @Override
    public CoffeeMachineTestSpec deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var object = json.getAsJsonObject();
        var machine = object.getAsJsonObject("machine");

        int slots = machine.getAsJsonObject("outlets").get("count_n").getAsInt();

        var ingredients = new HashMap<String, Integer>();

        for (var ingredient : machine.getAsJsonObject("total_items_quantity").entrySet()) {
            ingredients.put(ingredient.getKey(), ingredient.getValue().getAsInt());
        }

        var beverages = new HashMap<String, Map<String, Integer>>();

        for (var beverage : machine.getAsJsonObject("beverages").entrySet()) {
            String name = beverage.getKey();

            var recipeIngredient = new HashMap<String, Integer>();
            for (var ingredient : beverage.getValue().getAsJsonObject().entrySet()) {
                recipeIngredient.put(ingredient.getKey(), ingredient.getValue().getAsInt());
            }

            beverages.put(name, recipeIngredient);
        }

        return new CoffeeMachineTestSpec(slots, ingredients, beverages);
    }
}
