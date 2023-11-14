package christmas.view;

import christmas.utils.Constants;

enum OutputConsoleMessage {
    BENEFIT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + Constants.LINE_SEPARATOR),
    ORDER_HEADER(Constants.LINE_SEPARATOR + "<주문 메뉴>" + Constants.LINE_SEPARATOR),
    ORDER_ITEM("%s %d개" + Constants.LINE_SEPARATOR),
    TOTAL_ORDER_AMOUNT_HEADER(
        Constants.LINE_SEPARATOR + "<할인 전 총주문 금액>" + Constants.LINE_SEPARATOR),
    TOTAL_ORDER_AMOUNT("%,d원" + Constants.LINE_SEPARATOR),
    GIFT_MENU_HEADER(Constants.LINE_SEPARATOR + "<증정 메뉴>" + Constants.LINE_SEPARATOR),
    GIFT_MENU_ITEM("샴페인 1개" + Constants.LINE_SEPARATOR),
    BENEFIT_DETAILS_HEADER(Constants.LINE_SEPARATOR + "<혜택 내역>" + Constants.LINE_SEPARATOR),
    BENEFIT_DETAILS_ITEM("%s: -%,d원" + Constants.LINE_SEPARATOR),
    TOTAL_BENEFIT_AMOUNT(Constants.LINE_SEPARATOR + "<총혜택 금액>" + Constants.LINE_SEPARATOR),
    TOTAL_BENEFIT_AMOUNT_ITEM("-%,d원" + Constants.LINE_SEPARATOR),
    TOTAL_BENEFIT_AMOUNT_IS_ZERO("0원" + Constants.LINE_SEPARATOR),
    FINAL_AMOUNT_HEADER(Constants.LINE_SEPARATOR + "<할인 후 예상 결제 금액>" + Constants.LINE_SEPARATOR),
    FINAL_AMOUNT_ITEM("%,d원" + Constants.LINE_SEPARATOR),
    EVENT_BADGE_HEADER(Constants.LINE_SEPARATOR + "<12월 이벤트 배지>" + Constants.LINE_SEPARATOR),
    EVENT_BADGE_ITEM("%s" + Constants.LINE_SEPARATOR),
    NOTHING("없음" + Constants.LINE_SEPARATOR);

    private final String message;

    OutputConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
