package christmas.service;

import christmas.model.DecemberDiscountCalendar;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.in.VisitDate;
import christmas.utils.Constants;
import java.util.Map.Entry;

public class OrderCalculator {

    private final VisitDate visitDate;
    private final Order order;

    public OrderCalculator(final VisitDate visitDate, final Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public int calculateTotalOrderAmount() {
        return order.calculateTotalOrderAmount();
    }

    public int calculateChristmasDayDiscountAmount() {
        if(!isEventTarget()){
            return Constants.NO_DISCOUNT;
        }
        DecemberDiscountCalendar discountDay =
            DecemberDiscountCalendar.fromVisitDate(visitDate.visitDate());
        return discountDay.getChristmasDdayDiscount();
    }

    public int calculateDecemberDayOfWeekDiscountAmount() {
        if(!isEventTarget()){
            return Constants.NO_DISCOUNT;
        }
        DecemberDiscountCalendar discountDay =
            DecemberDiscountCalendar.fromVisitDate(visitDate.visitDate());
        int discountItemCount = order.orderItems().entrySet().stream()
            .filter(
                entry -> entry.getKey().getCategory() == discountDay.getWeekdayDiscountCategory())
            .mapToInt(Entry::getValue)
            .sum();
        return discountItemCount * Constants.DAY_OF_WEEK_DISCOUNT_AMOUNT;
    }

    public int calculateDecemberSpecialDiscountAmount() {
        if(!isEventTarget()){
            return Constants.NO_DISCOUNT;
        }
        DecemberDiscountCalendar discountDay =
            DecemberDiscountCalendar.fromVisitDate(visitDate.visitDate());
        if (discountDay.isSpecialDiscount()) {
            return Constants.SPECIAL_DISCOUNT_AMOUNT;
        }
        return Constants.NO_DISCOUNT;
    }

    public boolean isEligibleForChampagneComplimentaryEvent() {
        return order.calculateTotalOrderAmount() >= Constants.SHAMPAGNE_EVENT_THRESHOLD_PRICE;
    }

    public int calculateTotalDiscountBenefitAmount() {
        if (isEligibleForChampagneComplimentaryEvent()) {
            return calculateTotalCashDiscount() + Menu.CHAMPAGNE.getPrice();
        }
        return calculateTotalCashDiscount();
    }

    public int calculateExpectedPaymentAfterDiscount() {
        return calculateTotalOrderAmount() - calculateTotalCashDiscount();
    }

    private int calculateTotalCashDiscount() {
        return calculateDecemberDayOfWeekDiscountAmount() + calculateDecemberSpecialDiscountAmount()
            + calculateChristmasDayDiscountAmount();
    }

    private boolean isEventTarget(){
        return order.calculateTotalOrderAmount() >= Constants.EVENT_ELIGIBILITY_THRESHOLD_PRICE;
    }


}
