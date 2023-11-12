package christmas.model.calculator;

import christmas.model.data.DecemberEventCalendar;
import christmas.model.data.Menu.Category;
import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;
import java.util.Map.Entry;

public class DecemberDayOfWeekDiscount implements DiscountCalculator {

    private static final String WEEKDAY_EVENT_NAME = "평일 할인";
    private static final String WEEKEND_EVENT_NAME = "주말 할인";
    private static final boolean IS_CASH_DISCOUNT = true;


    private int calculateDecemberDayOfWeekDiscountAmount(final int visitDate, final Order order) {
        DecemberEventCalendar discountDay = DecemberEventCalendar.fromVisitDate(visitDate);
        int discountItemCount = order.orderItems().entrySet().stream()
            .filter(entry -> entry.getKey().getCategory() == discountDay.getWeekdayDiscountCategory())
            .mapToInt(Entry::getValue)
            .sum();
        return discountItemCount * Constants.DAY_OF_WEEK_DISCOUNT_AMOUNT;
    }

    private String determineEventType(final int visitDate) {
        DecemberEventCalendar discountDay = DecemberEventCalendar.fromVisitDate(visitDate);
        if (discountDay.getWeekdayDiscountCategory() == Category.DESSERT) {
            return WEEKDAY_EVENT_NAME;
        }
        return WEEKEND_EVENT_NAME;
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
