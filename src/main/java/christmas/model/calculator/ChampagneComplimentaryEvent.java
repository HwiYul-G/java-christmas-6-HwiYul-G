package christmas.model.calculator;

import christmas.model.data.Menu;
import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;

public class ChampagneComplimentaryEvent implements DiscountCalculator {

    private static final String EVENT_NAME = "증정 이벤트";
    private static final boolean IS_CASH_DISCOUNT = false;

    private boolean isEligibleForChampagneComplimentaryEvent(final Order order) {
        return order.calculateTotalOrderAmount() >= Constants.SHAMPAGNE_EVENT_THRESHOLD_PRICE;
    }

    @Override
    public DiscountResult calculateDiscount(final int visitDate, final Order order) {
        return new DiscountResult(EVENT_NAME, Menu.CHAMPAGNE.getPrice(), IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(int visitDate, Order order) {
        return isEligibleForChampagneComplimentaryEvent(order) && order.isEventTarget();
    }
}
