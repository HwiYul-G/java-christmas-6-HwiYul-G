package christmas.model.calculator;

import christmas.model.data.DecemberEventCalendar;
import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;

public class DecemberSpecialDiscount implements DiscountCalculator {

    private static final String EVENT_NAME = "특별 할인";
    private static final boolean IS_CASH_DISCOUNT = true;

    private boolean isSpecialDiscountAmountApplicable(final int visitDay) {
        DecemberEventCalendar discountDay = DecemberEventCalendar.fromVisitDate(visitDay);
        return discountDay.isSpecialDiscount();
    }

    @Override
    public DiscountResult calculateDiscount(int visitDate, Order order) {
        return new DiscountResult(EVENT_NAME, Constants.SPECIAL_DISCOUNT_AMOUNT, IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(int visitDate, Order order) {
        return order.isEventTarget() && isSpecialDiscountAmountApplicable(visitDate);
    }
}
