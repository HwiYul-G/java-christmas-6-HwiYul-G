package christmas.model.calculator;

import christmas.model.data.Menu;
import christmas.model.Order;
import christmas.model.DiscountResult;

public final class FreeChampagneEvent implements Discount {

    private static final String EVENT_NAME = "증정 이벤트";
    private static final boolean IS_CASH_DISCOUNT = false;
    private static final int EVENT_THRESHOLD_PRICE = 120_000;

    private boolean isEligibleForChampagneComplimentaryEvent(final Order order) {
        return order.calculateTotalOrderAmount() >= EVENT_THRESHOLD_PRICE;
    }

    @Override
    public DiscountResult calculateDiscount(final int visitDate, final Order order) {
        return new DiscountResult(EVENT_NAME, Menu.CHAMPAGNE.getPrice(), IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(final int visitDate, final Order order) {
        return isEligibleForChampagneComplimentaryEvent(order) && order.isEventTarget();
    }
}
