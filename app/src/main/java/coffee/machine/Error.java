package coffee.machine;

public record Error(Code code, String entity) {
    public enum Code {
        ITEM_INSUFFICIENT,
        ITEM_UNAVAILABLE,
        UNKNOWN;
    }
}
