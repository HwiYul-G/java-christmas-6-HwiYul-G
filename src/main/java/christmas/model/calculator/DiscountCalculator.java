package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;

public interface DiscountCalculator {

    DiscountResult calculateDiscount(final int visitDate, final Order order);

    boolean isApplicable(final int visitDate, final Order order);
}
