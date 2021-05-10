package coffee.machine.spec;


import java.util.HashMap;
import java.util.Map;

public record CoffeeMachineTestSpec(int slotCount, HashMap<String, Integer> ingredients,
                                    HashMap<String, Map<String, Integer>> beverages) {

}
