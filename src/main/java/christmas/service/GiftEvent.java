package christmas.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;
import christmas.domain.data.Menu;

public class GiftEvent implements Event {

    private static final String EVENT_NAME = "증정 이벤트";
    private static final int GIFT_THRESHOLD = 120_000;

    public boolean isGiftTarget(int totalPrice) {
        return totalPrice >= GIFT_THRESHOLD;
    }

    @Override
    public EventResult calculateEventResult(int date, Orders orders) {
        if (isGiftTarget(orders.calculateTotalPrice())) {
            return new EventResult(EVENT_NAME, Menu.CHAMPAGNE.getPrice());
        }
        return new EventResult(EVENT_NAME, 0);
    }
}
