package christmas.util;

public enum ExceptionMessage {
    INVALID_("");

    public static final String BASE_MESSAGE = "[ERROR] : %s";
    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
