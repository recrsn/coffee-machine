package coffee.machine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Item> items;

    public Inventory(Map<String, Integer> items) {
        this.items = new HashMap<>();
        this.restock(items);
    }

    public void restock(Map<String, Integer> newItems) {
        synchronized (items) {
            for (var entry : newItems.entrySet()) {
                String item = entry.getKey();
                Integer quantity = entry.getValue();
                if (items.containsKey(item)) {
                    items.get(item).restock(quantity);
                } else {
                    items.put(item, new Item(item, quantity));
                }
            }
        }
    }

    public Result consume(Map<String, Integer> quantities) {
        synchronized (items) {
            // check inventory
            for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
                String item = entry.getKey();
                Integer v = entry.getValue();

                if (!items.containsKey(item)) {
                    return Result.error(Error.Code.ITEM_UNAVAILABLE, item);
                }

                if (!items.get(item).ensure(v)) {
                    return Result.error(Error.Code.ITEM_INSUFFICIENT, item);
                }
            }

            // consume
            quantities.forEach((key, value) -> items.get(key).consume(value));
        }

        return Result.success();
    }

    public static class Item {
        private final String name;
        private int quantity;

        public Item(String name, int initialQuantity) {
            this.name = name;
            this.quantity = initialQuantity;
        }

        public void restock(int value) {
            quantity += value;
        }

        public void consume(int value) {
            if (value > quantity) {
                throw new IllegalStateException(this.name);
            }
            quantity -= value;
        }

        public boolean ensure(Integer value) {
            return quantity >= value;
        }
    }
}
