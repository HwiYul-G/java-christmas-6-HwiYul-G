package christmas.service;

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

public class CalculatorService {

    private int visitDate;
    private OrderItems orderItems;

    private final OrderCalculator orderCalculator;

    public CalculatorService() {
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

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public void setOrder(OrderItems orderItems) {
        this.orderItems = orderItems;
    }


    private OrderCalculator createOrderCalculator(final int visitDate, final Order order) {
        List<Discount> discountCalculators = List.of(new ChristmasDayDiscount(),
            new DecemberDayOfWeekDiscount(), new DecemberSpecialDiscount(),
            new FreeChampagneEvent());
        return new OrderCalculator(visitDate, order, discountCalculators);
    }

}
