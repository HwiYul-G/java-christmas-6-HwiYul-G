package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;
import java.util.Arrays;
import java.util.List;

public final class DecemberSpecialDiscount implements Discount {

    private static final String EVENT_NAME = "특별 할인";
    private static final boolean IS_CASH_DISCOUNT = true;
    private static final List<Integer> SPECIAL_EVENT_DATE = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;

    @Override
    public DiscountResult calculateDiscount(final int visitDate, final Order order) {
        return new DiscountResult(EVENT_NAME, SPECIAL_DISCOUNT_AMOUNT, IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(final int visitDate, final Order order) {
        return SPECIAL_EVENT_DATE.contains(visitDate) && order.isEventTarget();
    }
}
