package coffee.machine.spec;

import coffee.machine.Result;

import java.util.concurrent.CompletableFuture;

public class TestResult {
    private final String name;
    private final CompletableFuture<Result> completableFuture;

    public TestResult(String name, CompletableFuture<Result> completableFuture) {
        this.name = name;
        this.completableFuture = completableFuture;
    }

    public CompletableFuture<String> result() {
        return completableFuture
                .thenApply(result -> {
                    if (result.hasError()) {
                        return formatError(result);
                    }

                    return name + " is prepared";
                });
    }

    private String formatError(Result result) {
        return switch (result.error().code()) {
            case UNKNOWN -> "unknown error";
            case ITEM_UNAVAILABLE -> name + " cannot be prepared because " + result.error()
                    .entity() + " is not available";
            case ITEM_INSUFFICIENT -> name + " cannot be prepared because " + result.error()
                    .entity() + " is not sufficient";
        };
    }
}
