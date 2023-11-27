package christmas.controller;

import christmas.model.DiscountResult;
import christmas.model.OrderItems;
import christmas.model.data.EventBadge;
import christmas.view.OutputView;
import java.util.List;

public class OutputController {

    private final OutputView outputView;

    public OutputController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void printOrderMenu(final OrderItems orderItems){
        outputView.printOrderMenu(orderItems);
    }

    public void printTotalOrderAmountBeforeDiscount(final int totalOrderAmount){
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmount);
    }

    public void printGiftMenu(final boolean isGiftTarget){
        outputView.printGiftMenu(isGiftTarget);
    }

    public void printBenefitDetails(final List<DiscountResult> discountResults){
        outputView.printBenefitDetails(discountResults);
    }

    public void printTotalBenefitAmount(final int discountBenefitAmount){
        outputView.printTotalBenefitAmount(discountBenefitAmount);
    }

    public void printExpectedPaymentAfterDiscount(final int expectedPayment){
        outputView.printExpectedPaymentAfterDiscount(expectedPayment);
    }

    public void printEventBadge(final int discountBenefitAmount){
        EventBadge badge = EventBadge.getBadgeByDiscountAmount(discountBenefitAmount);
        outputView.printEventBadge(badge.getBadge());
    }

}
