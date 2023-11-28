package christmas.service;

import christmas.model.Order;
import christmas.model.OrderItems;
import christmas.model.calculator.CalculationResult;
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

    public CalculatorService() {
    }

    public CalculationResult calculate() {
        OrderCalculator orderCalculator = createOrderCalculator(visitDate, orderItems.toOrder());
        return orderCalculator.calculate();
    }

    private OrderCalculator createOrderCalculator(final int visitDate, final Order order) {
        List<Discount> discountCalculators = List.of(new ChristmasDayDiscount(),
            new DecemberDayOfWeekDiscount(), new DecemberSpecialDiscount(),
            new FreeChampagneEvent());
        return new OrderCalculator(visitDate, order, discountCalculators);
    }


    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }
}
