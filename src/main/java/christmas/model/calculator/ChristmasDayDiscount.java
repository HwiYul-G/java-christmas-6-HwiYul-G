package christmas.model.calculator;

import christmas.model.data.DecemberEventCalendar;
import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;

public class ChristmasDayDiscount implements DiscountCalculator {

    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private static final boolean IS_CASH_DISCOUNT = true;

    private int calculateChristmasDayDiscountAmount(final int visitDate) {
        DecemberEventCalendar discountDay = DecemberEventCalendar.fromVisitDate(visitDate);
        return discountDay.getChristmasDdayDiscount();
    }

    @Override
    public DiscountResult calculateDiscount(final int visitDate, final Order order) {
        return new DiscountResult(EVENT_NAME, calculateChristmasDayDiscountAmount(visitDate), IS_CASH_DISCOUNT);
    }

    @Override
    public boolean isApplicable(int visitDate, Order order) {
        return DecemberEventCalendar.fromVisitDate(visitDate).getChristmasDdayDiscount()
            != Constants.NO_DISCOUNT && order.isEventTarget();
    }

}
