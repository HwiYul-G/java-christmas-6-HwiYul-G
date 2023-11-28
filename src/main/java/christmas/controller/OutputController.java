package christmas.controller;

import christmas.model.DiscountResult;
import christmas.model.OrderItems;
import christmas.model.calculator.CalculationResult;
import christmas.model.data.EventBadge;
import christmas.service.CalculatorService;
import christmas.view.OutputView;
import java.util.List;

public class OutputController {

    private final OutputView outputView;

    public OutputController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void run(final CalculationResult calculationResult){
        printOrderMenu(calculationResult.orderItems());

        printTotalOrderAmountBeforeDiscount(calculationResult.totalOrderAmount());

        printGiftMenu(calculationResult.eligibleForChampagne());
        printBenefitDetails(calculationResult.discountDetails());
        printTotalBenefitAmount(calculationResult.totalDiscountAmount());
        printExpectedPaymentAfterDiscount(calculationResult.expectedPayment());
        printEventBadge(calculationResult.totalDiscountAmount());
    }

    private void printOrderMenu(final OrderItems orderItems) {
        outputView.printOrderMenu(orderItems);
    }

    private void printTotalOrderAmountBeforeDiscount(final int totalOrderAmount) {
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmount);
    }

    private void printGiftMenu(final boolean isGiftTarget) {
        outputView.printGiftMenu(isGiftTarget);
    }

    private void printBenefitDetails(final List<DiscountResult> discountResults) {
        outputView.printBenefitDetails(discountResults);
    }

    private void printTotalBenefitAmount(final int discountBenefitAmount ) {
        outputView.printTotalBenefitAmount(discountBenefitAmount);
    }

    private void printExpectedPaymentAfterDiscount(final int expectedPayment) {
        outputView.printExpectedPaymentAfterDiscount(expectedPayment);
    }

    private void printEventBadge(final int discountBenefitAmount) {
        EventBadge badge = EventBadge.getBadgeByDiscountAmount(discountBenefitAmount);
        outputView.printEventBadge(badge.getBadge());
    }

}
