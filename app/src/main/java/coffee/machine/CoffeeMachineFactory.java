package coffee.machine;

import coffee.machine.CoffeeMachine;
import coffee.machine.Inventory;

import java.util.HashMap;

public class CoffeeMachineFactory {
    public static CoffeeMachine create(int slots, HashMap<String, Integer> ingredients) {
        Inventory inventory = new Inventory(ingredients);
        return new CoffeeMachine(slots, inventory);
    }
}
