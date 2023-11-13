package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;
import java.util.Arrays;
import java.util.List;

public class DecemberSpecialDiscount implements DiscountCalculator {

    private static final String EVENT_NAME = "특별 할인";
    private static final boolean IS_CASH_DISCOUNT = true;
    private static final List<Integer> SPECIAL_EVENT_DATE = Arrays.asList(3, 10, 17, 24, 25, 31);


    @Override
    public DiscountResult calculateDiscount(int visitDate, Order order) {
        return new DiscountResult(EVENT_NAME, Constants.SPECIAL_DISCOUNT_AMOUNT, IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(int visitDate, Order order) {
        return SPECIAL_EVENT_DATE.contains(visitDate) && order.isEventTarget();
    }
}
