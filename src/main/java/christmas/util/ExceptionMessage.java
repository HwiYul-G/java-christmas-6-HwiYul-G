package christmas.util;

public enum ExceptionMessage {
    INVALID_CATEGORY("존재하지 않는 카테고리입니다."),
    INVALID_MENU("존재하지 않는 메뉴입니다."),
    INVALID_ORDERS("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_VISITDATE("방문 날짜는 숫자만 입력 가능합니다."),
    INVALID_ORDERS_QUANTITY("주문 수량은 20개를 초과할 수 없습니다."),
    INVALID_VISITDATE_NUMBER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_VISITDATE_MIN_RANGE("방문 날짜는 1일 이상만 가능합니다."),
    INVALID_VISITDATE_MAX_RANGE("방문 날짜는 31일 이하만 가능합니다."),
    INVALID_ORDER_QUANTITY("메뉴당 주문 수량은 1 이상만 가능합니다."),
    INVALID_ORDERS_DUPLICATION("입력시 메뉴를 중복되게 입력할 수 없습니다.");

    public static final String BASE_MESSAGE = "[ERROR] %s";
    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format(BASE_MESSAGE, message);
    }
}
