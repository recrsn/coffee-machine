package coffee.machine.spec;

import java.util.Map;

public record Beverage(String name, Map<String, Integer> ingredients) {
}
