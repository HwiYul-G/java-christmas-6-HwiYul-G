package christmas.controller;

import christmas.model.DiscountResult;
import christmas.model.OrderItems;
import christmas.model.data.EventBadge;
import christmas.service.CalculatorService;
import christmas.view.OutputView;
import java.util.List;

public class OutputController {

    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public OutputController(final OutputView outputView,
        final CalculatorService calculatorService) {
        this.outputView = outputView;
        this.calculatorService = calculatorService;
    }

    public void run(){
        printOrderMenu();

        printTotalOrderAmountBeforeDiscount();

        printGiftMenu();
        printBenefitDetails();
        printTotalBenefitAmount();
        printExpectedPaymentAfterDiscount();
        printEventBadge();
    }

    private void printOrderMenu() {
        OrderItems orderItems = calculatorService.getOrderItems();
        outputView.printOrderMenu(orderItems);
    }

    private void printTotalOrderAmountBeforeDiscount() {
        int totalOrderAmount = calculatorService.calculateTotalOrderAmount();
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmount);
    }

    private void printGiftMenu() {
        boolean isGiftTarget = calculatorService.isEligibleForChampagne();
        outputView.printGiftMenu(isGiftTarget);
    }

    private void printBenefitDetails() {
        List<DiscountResult> discountResults = calculatorService.calculateDiscountDetails();
        outputView.printBenefitDetails(discountResults);
    }

    private void printTotalBenefitAmount() {
        int discountBenefitAmount = calculatorService.calculateTotalDiscountBenefitAmount();
        outputView.printTotalBenefitAmount(discountBenefitAmount);
    }

    private void printExpectedPaymentAfterDiscount() {
        int expectedPayment = calculatorService.calculateExpectedPaymentAfterDiscount();
        outputView.printExpectedPaymentAfterDiscount(expectedPayment);
    }

    private void printEventBadge() {
        int discountBenefitAmount = calculatorService.calculateTotalDiscountBenefitAmount();
        EventBadge badge = EventBadge.getBadgeByDiscountAmount(discountBenefitAmount);
        outputView.printEventBadge(badge.getBadge());
    }

}
