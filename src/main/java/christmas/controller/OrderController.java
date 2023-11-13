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
import christmas.model.data.EventBadge;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;


    public OrderController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.printStartComment();

        int visitDate = inputVisitDate();
        OrderItems orderItems = inputOrderItems();

        processOrderPreview(visitDate, orderItems);
        processOrder(visitDate, orderItems.toOrder());
    }


    private int inputVisitDate() {
        do {
            try {
                return inputView.inputVisitDate().visitDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private OrderItems inputOrderItems() {
        do {
            try {
                return inputView.inputOrderInfo();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private void processOrderPreview(final int visitDate, final OrderItems orderItems) {
        outputView.printBenefitPreview(visitDate);
        outputView.printOrderMenu(orderItems);
    }

    private void processOrder(final int visitDate, Order order) {
        OrderCalculator orderCalculator = createOrderCalculator(visitDate, order);
        processInitialOrderDetails(orderCalculator);
        processDiscountDetails(orderCalculator);
    }

    private void processInitialOrderDetails(final OrderCalculator orderCalculator) {
        int totalOrderAmount = orderCalculator.calculateTotalOrderAmount();
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmount);

        boolean isGiftTarget = orderCalculator.isEligibleForChampagne();
        outputView.printGiftMenu(isGiftTarget);
    }

    private void processDiscountDetails(final OrderCalculator orderCalculator) {
        List<DiscountResult> discountResults = orderCalculator.calculateDiscountDetails();
        outputView.printBenefitDetails(discountResults);

        int discountBenefitAmount = orderCalculator.calculateTotalDiscountBenefitAmount();
        outputView.printTotalBenefitAmount(discountBenefitAmount);

        int expectedPayment = orderCalculator.calculateExpectedPaymentAfterDiscount();
        outputView.printExpectedPaymentAfterDiscount(expectedPayment);

        printEventBadge(discountBenefitAmount);
    }

    private void printEventBadge(final int totalDiscountBenefitAmount) {
        EventBadge badge = EventBadge.getBadgeByDiscountAmount(totalDiscountBenefitAmount);
        outputView.printEventBadge(badge.getBadge());
    }

    private OrderCalculator createOrderCalculator(final int visitDate, final Order order) {
        List<Discount> discountCalculators = List.of(new ChristmasDayDiscount(),
            new DecemberDayOfWeekDiscount(), new DecemberSpecialDiscount(),
            new FreeChampagneEvent());

        return new OrderCalculator(visitDate, order, discountCalculators);
    }


}
