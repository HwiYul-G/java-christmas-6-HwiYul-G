package christmas.util;

public enum ExceptionMessage {
    INVALID_CATEGORY("존재하지 않는 카테고리입니다.");

    public static final String BASE_MESSAGE = "[ERROR] : %s";
    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
