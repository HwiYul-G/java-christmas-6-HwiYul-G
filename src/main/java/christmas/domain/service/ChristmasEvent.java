package christmas.domain.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;

public class ChristmasEvent implements Event {
    private static final String EVENT_NAME = "크리스마스 디데이 할인";

    private static final int CHRISTMAS_DAY = 25;
    private static final int START_DISCOUNT_PRICE = 1000;
    private static final int DISCOUNT_PRICE_STEP = 100;

    private int calculateDiscountPrice(int date) {
        if (date <= CHRISTMAS_DAY) {
            return START_DISCOUNT_PRICE + DISCOUNT_PRICE_STEP * (date - 1);
        }
        return 0;
    }

    @Override
    public boolean isAvailable(Orders orders) {
        return orders.isEventTarget();
    }

    @Override
    public EventResult calculateEventResult(int date, Orders orders) {
        if(isAvailable(orders)) {
            int discountPrice = calculateDiscountPrice(date);
            return new EventResult(EVENT_NAME, discountPrice);
        }
        return new EventResult(EVENT_NAME, 0);
    }
}
