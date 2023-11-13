package christmas.view;

import christmas.model.DiscountResult;
import christmas.model.OrderItem;
import christmas.model.OrderItems;
import java.util.List;

public class OutputView {

    public void printBenefitPreview(final int visitDate) {
        System.out.printf(OutputConsoleMessage.BENEFIT_PREVIEW.getMessage(), visitDate);
    }

    public void printOrderMenu(final OrderItems orderItems) {
        System.out.printf(OutputConsoleMessage.ORDER_HEADER.getMessage());
        for (OrderItem orderItem : orderItems.orderItems()) {
            System.out.printf(OutputConsoleMessage.ORDER_ITEM.getMessage(), orderItem.menuName(),
                orderItem.quantity());
        }
    }

    public void printTotalOrderAmountBeforeDiscount(final int totalAmountBeforeDiscount) {
        System.out.printf(OutputConsoleMessage.TOTAL_ORDER_AMOUNT_HEADER.getMessage());
        System.out.printf(OutputConsoleMessage.TOTAL_ORDER_AMOUNT.getMessage(),
            totalAmountBeforeDiscount);
    }

    public void printGiftMenu(final boolean isGiftTarget) {
        System.out.printf(OutputConsoleMessage.GIFT_MENU_HEADER.getMessage());
        if(!isGiftTarget){
            System.out.printf(OutputConsoleMessage.NOTHING.getMessage());
            return;
        }
        System.out.printf(OutputConsoleMessage.GIFT_MENU_ITEM.getMessage());
    }

    public void printBenefitDetails(final List<DiscountResult> discountResults) {
        System.out.printf(OutputConsoleMessage.BENEFIT_DETAILS_HEADER.getMessage());
        if (discountResults.isEmpty()) {
            System.out.printf(OutputConsoleMessage.NOTHING.getMessage());
            return;
        }
        for (DiscountResult discountResult : discountResults) {
            System.out.printf(OutputConsoleMessage.BENEFIT_DETAILS_ITEM.getMessage(),
                discountResult.eventName(), discountResult.discountAmount());
        }
    }

    public void printTotalBenefitAmount(final int benefitAmount) {
        System.out.printf(OutputConsoleMessage.TOTAL_BENEFIT_AMOUNT.getMessage());
        if (benefitAmount == 0) {
            System.out.printf(OutputConsoleMessage.TOTAL_BENEFIT_AMOUNT_IS_ZERO.getMessage());
            return;
        }
        System.out.printf(OutputConsoleMessage.TOTAL_BENEFIT_AMOUNT_ITEM.getMessage(),
            benefitAmount);
    }

    public void printExpectedPaymentAfterDiscount(final int paymentAfterDiscount) {
        System.out.printf(OutputConsoleMessage.FINAL_AMOUNT_HEADER.getMessage());
        System.out.printf(OutputConsoleMessage.FINAL_AMOUNT_ITEM.getMessage(),
            paymentAfterDiscount);
    }

    public void printEventBadge(final String badge) {
        System.out.printf(OutputConsoleMessage.EVENT_BADGE_HEADER.getMessage());
        System.out.printf(OutputConsoleMessage.EVENT_BADGE_ITEM.getMessage(), badge);
    }

}
