package coffee.machine.spec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;

public class CoffeeMachineTestSpecFactory {
    public static CoffeeMachineTestSpec loadSpec(Reader reader) {
        return gson().fromJson(reader, CoffeeMachineTestSpec.class);
    }

    private static Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(CoffeeMachineTestSpec.class, new CoffeeMachineSpecDeserializer())
                .create();
    }
}
