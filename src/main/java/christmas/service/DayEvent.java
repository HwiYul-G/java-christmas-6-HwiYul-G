package christmas.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;
import christmas.domain.data.Category;
import java.util.List;

public class DayEvent implements Event {

    private static final String WEEKEND_EVENT_NAME = "주말 할인";
    private static final String WEEKDAY_EVENT_NAME = "평일 할인";

    private static final List<Integer> WEEKEND_DATES = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final int DISCOUNT_PRICE = 2023;

    private boolean isWeekendDate(int date) {
        return WEEKEND_DATES.contains(date);
    }

    private Category eventCategory(int date) {
        if (isWeekendDate(date)) {
            return Category.MAIN;
        }
        return Category.DESSERT;
    }

    private int calculateDiscountPrice(int date, Orders orders) {
        // MENU의 카테고리를 확인하고, 그 품목의 수량만큼 할인 금액 계산
        return orders.orders().stream()
            .filter(order -> order.menu().getCategory().equals(eventCategory(date)))
            .mapToInt(order -> order.quantity() * DISCOUNT_PRICE)
            .sum();
    }

    @Override
    public EventResult calculateEventResult(int date, Orders orders) {
        int discountPrice = calculateDiscountPrice(date, orders);
        if (isWeekendDate(date)) {
            return new EventResult(WEEKEND_EVENT_NAME, discountPrice);
        }
        return new EventResult(WEEKDAY_EVENT_NAME, discountPrice);
    }


}
