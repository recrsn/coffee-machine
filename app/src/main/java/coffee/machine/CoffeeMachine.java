package coffee.machine;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

public class CoffeeMachine {
    public static final int COFFEE_MACHINE_MS = 100;
    private final Inventory inventory;
    private final Semaphore semaphore;

    public CoffeeMachine(int slots, Inventory inventory) {
        this.inventory = inventory;
        this.semaphore = new Semaphore(slots);
    }

    public CompletableFuture<Result> brew(Map<String, Integer> recipe) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                semaphore.acquire();
                return inventory.consume(recipe)
                        .andThen(this::makeCoffee);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return Result.error(Error.Code.UNKNOWN, null);
            } finally {
                semaphore.release();
            }
        });
    }

    private Result makeCoffee() {
        try {
            Thread.sleep(COFFEE_MACHINE_MS);
            return Result.success();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
            return Result.error(Error.Code.UNKNOWN, null);
        }
    }
}
