package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;

public sealed interface Discount permits ChristmasDayDiscount, DecemberDayOfWeekDiscount,
    DecemberSpecialDiscount, FreeChampagneEvent {

    DiscountResult calculateDiscount(final int visitDate, final Order order);

    boolean isApplicable(final int visitDate, final Order order);
}
