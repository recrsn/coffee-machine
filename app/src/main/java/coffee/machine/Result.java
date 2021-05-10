package coffee.machine;

import java.util.function.Supplier;

public record Result(Error error) {
    public static Result error(Error.Code code, String entity) {
        return new Result(new Error(code, entity));
    }

    public static Result success() {
        return new Result(null);
    }

    public Result andThen(Supplier<Result> resultSupplier) {
        if (this.hasError()) {
            return this;
        }

        return resultSupplier.get();
    }

    public boolean hasError() {
        return error != null;
    }
}
