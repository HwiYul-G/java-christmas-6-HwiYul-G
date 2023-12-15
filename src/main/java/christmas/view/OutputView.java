package christmas.view;

import christmas.domain.EventResult;
import christmas.domain.Orders;
import christmas.domain.data.EventBadge;
import christmas.domain.data.Menu;
import java.util.List;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printStart() {
        System.out.printf(Message.OUTPUT_START.message);
    }

    public void printBenefitPreview(int date) {
        System.out.printf(Message.OUTPUT_BENEFIT_PREVIEW.message, date);
    }

    public void printOrderMenu(Orders orders) {
        System.out.printf(Message.OUTPUT_ORDER_MENU.message);
         orders.orders().forEach(order -> {
             System.out.printf(Message.OUTPUT_ORDER_MENU_DETAIL.message, order.menu().getName(), order.quantity());
         });
    }

    public void printBeforeBenefitTotalPrice(int totalPrice){
        System.out.printf(Message.OUTPUT_BEFORE_BENEFIT_TOTAL_PRICE.message);
        System.out.printf(Message.OUTPUT_BEFORE_BENEFIT_TOTAL_PRICE_DETAIL.message, totalPrice);
    }

    public void printGiftMenu(Menu giftMenu, int quantity){
        // TODO : gifitMenu가 없으면 "없음"출력
        if(quantity == 0){
            System.out.printf(Message.OUTPUT_NOTHING.message);
            return;
        }
        System.out.printf(Message.OUTPUT_GIFT_MENU.message);
        System.out.printf(Message.OUTPUT_GIFT_MENU_DETAIL.message, giftMenu.getName(), quantity);
    }

    public void printEvent(List<EventResult> eventResults){
        System.out.printf(Message.OUTPUT_EVENT.message);
        if(eventResults.isEmpty()){
            System.out.printf(Message.OUTPUT_NOTHING.message);
            return;
        }
        eventResults.forEach(eventResult -> {
            System.out.printf(Message.OUTPUT_EVENT_DETAIL.message, eventResult.name(), -1*eventResult.benefitPrice());
        });
    }

    public void printTotalBenefit(int totalBenefit){
        System.out.printf(Message.OUTPUT_TOTAL_BENEFIT.message);
        System.out.printf(Message.OUTPUT_TOTAL_BENEFIT_DETAIL.message, -1*totalBenefit);
    }

    public void printExpectedPay(int expectedPay){
        System.out.printf(Message.OUTPUT_EXPECTED_PAY.message);
        System.out.printf(Message.OUTPUT_EXPECTED_PAY_DETAIL.message, expectedPay);
    }

    public void printEventBadge(EventBadge eventBadge){
        System.out.printf(Message.OUTPUT_EVENT_BADGE.message);
        System.out.printf(Message.OUTPUT_EVENT_BADGE_DETAIL.message, eventBadge.name());
    }

    // TODO : printf로 출력하는 것을 기본으로 할 것
    private enum Message {
        OUTPUT_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"),
        OUTPUT_NOTHING("없음"),
        OUTPUT_BENEFIT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
        OUTPUT_ORDER_MENU("\n<주문 메뉴>\n"),
        OUTPUT_ORDER_MENU_DETAIL("%s %d개\n"),
        OUTPUT_BEFORE_BENEFIT_TOTAL_PRICE("\n<할인 전 총주문 금액>\n"),
        OUTPUT_BEFORE_BENEFIT_TOTAL_PRICE_DETAIL("%d원\n"), // 1000단위 컴마
        OUTPUT_GIFT_MENU("\n<증정 메뉴>\n"),
        OUTPUT_GIFT_MENU_DETAIL("%s %d개\n"),
        OUTPUT_EVENT("\n<혜택 내역>\n"),
        OUTPUT_EVENT_DETAIL("%s: %d원\n"),
        OUTPUT_TOTAL_BENEFIT("\n<총혜택 금액>\n"),
        OUTPUT_TOTAL_BENEFIT_DETAIL("%d원\n"),
        OUTPUT_EXPECTED_PAY("\n<할인 후 예상 결제 금액>\n"),
        OUTPUT_EXPECTED_PAY_DETAIL("%d원\n"),
        OUTPUT_EVENT_BADGE("\n<12월 이벤트 배지>\n"),
        OUTPUT_EVENT_BADGE_DETAIL("%s\n");

        private final String message;

        Message(final String message) {
            this.message = message;
        }
    }

}