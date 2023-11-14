package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;

public final class ChristmasDayDiscount implements Discount {

    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private static final boolean IS_CASH_DISCOUNT = true;

    private static final int EVENT_START_DAY = 1;
    private static final int EVENT_LAST_DAY = 25;
    private static final int START_DISCOUNT_AMOUNT = 1000;
    private static final int STEP = 100;
    private static final int NO_DISCOUNT = 0;

    private int calculateChristmasDayDiscountAmount(final int visitDate, final Order order) {
        if(isApplicable(visitDate, order))
            return START_DISCOUNT_AMOUNT + STEP * (visitDate - EVENT_START_DAY);
        return NO_DISCOUNT;
    }

    @Override
    public DiscountResult calculateDiscount(final int visitDate, final Order order) {
        int discountAmount = calculateChristmasDayDiscountAmount(visitDate, order);
        return new DiscountResult(EVENT_NAME, discountAmount, IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(final int visitDate, Order order) {
        return visitDate >= EVENT_START_DAY && visitDate <= EVENT_LAST_DAY && order.isEventTarget();
    }

}
