package christmas.view;

enum OutputConsoleMessage {
    BENEFIT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    ORDER_HEADER("\n<주문 메뉴>\n"),
    ORDER_ITEM("%s %d개\n"),
    TOTAL_ORDER_AMOUNT_HEADER("\n<할인 전 총주문 금액>\n"),
    TOTAL_ORDER_AMOUNT("%,d원\n"),
    GIFT_MENU_HEADER("\n<증정 메뉴>\n"),
    GIFT_MENU_ITEM("샴페인 1개\n"),
    BENEFIT_DETAILS_HEADER("\n<혜택 내역>\n"),
    BENEFIT_DETAILS_ITEM("%s: -%,d원\n"),
    TOTAL_BENEFIT_AMOUNT("\n<총혜택 금액>\n"),
    TOTAL_BENEFIT_AMOUNT_ITEM("-%,d원\n"),
    TOTAL_BENEFIT_AMOUNT_IS_ZERO("0원\n"),
    FINAL_AMOUNT_HEADER("\n<할인 후 예상 결제 금액>\n"),
    FINAL_AMOUNT_ITEM("%,d원\n"),
    EVENT_BADGE_HEADER("\n<12월 이벤트 배지>\n"),
    EVENT_BADGE_ITEM("%s\n"),
    NOTHING("없음\n");

    private final String message;

    OutputConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
