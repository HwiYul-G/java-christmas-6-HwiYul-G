package christmas.model.calculator;

import christmas.model.data.Menu.Category;
import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

public class DecemberDayOfWeekDiscount implements DiscountCalculator {

    private static final String WEEKDAY_EVENT_NAME = "평일 할인";
    private static final String WEEKEND_EVENT_NAME = "주말 할인";
    private static final boolean IS_CASH_DISCOUNT = true;
    private static final List<Integer> WEEKEND_DAYS = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29,
        30);


    private int calculateDecemberDayOfWeekDiscountAmount(final int visitDate, final Order order) {
        Category discountCategory = determineDiscountCategory(visitDate);
        int discountItemCount = order.orderItems().entrySet().stream()
            .filter(entry -> entry.getKey().getCategory() == discountCategory)
            .mapToInt(Entry::getValue)
            .sum();
        return discountItemCount * Constants.DAY_OF_WEEK_DISCOUNT_AMOUNT;
    }

    private String determineEventType(final int visitDate) {
        if (isWeekend(visitDate)) {
            return WEEKEND_EVENT_NAME;
        }
        return WEEKDAY_EVENT_NAME;
    }

    private Category determineDiscountCategory(final int visitDate){
        if(isWeekend(visitDate)){
            return Category.MAIN;
        }
        return Category.DESSERT;
    }

    private boolean isWeekend(final int visitDate){
        return WEEKEND_DAYS.contains(visitDate);
    }

    @Override
    public DiscountResult calculateDiscount(final int visitDate, final Order order) {
        String eventType = determineEventType(visitDate);
        int discountAmount = calculateDecemberDayOfWeekDiscountAmount(visitDate, order);
        return new DiscountResult(eventType, discountAmount, IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(int visitDate, Order order) {
        return order.isEventTarget();
    }

}
