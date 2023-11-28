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

    public CalculatorService() {
    }

    public CalculationResult calculate(final int visitDate, final OrderItems orderItems){
        OrderCalculator orderCalculator = createOrderCalculator(visitDate, orderItems.toOrder());
        return orderCalculator.calculate();
    }

    private OrderCalculator createOrderCalculator(final int visitDate, final Order order) {
        List<Discount> discountCalculators = List.of(new ChristmasDayDiscount(),
            new DecemberDayOfWeekDiscount(), new DecemberSpecialDiscount(),
            new FreeChampagneEvent());
        return new OrderCalculator(visitDate, order, discountCalculators);
    }

}
