package christmas.controller;

import christmas.model.DiscountResult;
import christmas.model.Order;
import christmas.model.OrderItems;
import christmas.model.calculator.FreeChampagneEvent;
import christmas.model.calculator.ChristmasDayDiscount;
import christmas.model.calculator.DecemberDayOfWeekDiscount;
import christmas.model.calculator.DecemberSpecialDiscount;
import christmas.model.calculator.Discount;
import christmas.model.calculator.OrderCalculator;
import java.util.List;

public class CalculatorController {

    private final OrderCalculator orderCalculator;

    public CalculatorController(final int visitDate, final OrderItems orderItems) {
        orderCalculator = createOrderCalculator(visitDate, orderItems.toOrder());
    }

    public int calculateTotalOrderAmount() {
        return orderCalculator.calculateTotalOrderAmount();
    }

    public boolean isEligibleForChampagne() {
        return orderCalculator.isEligibleForChampagne();
    }

    public List<DiscountResult> calculateDiscountDetails() {
        return orderCalculator.calculateDiscountDetails();
    }

    public int calculateTotalDiscountBenefitAmount() {
        return orderCalculator.calculateTotalDiscountBenefitAmount();
    }

    public int calculateExpectedPaymentAfterDiscount() {
        return orderCalculator.calculateExpectedPaymentAfterDiscount();
    }

    private OrderCalculator createOrderCalculator(final int visitDate, final Order order) {
        List<Discount> discountCalculators = List.of(new ChristmasDayDiscount(),
            new DecemberDayOfWeekDiscount(), new DecemberSpecialDiscount(),
            new FreeChampagneEvent());
        return new OrderCalculator(visitDate, order, discountCalculators);
    }

}
