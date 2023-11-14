package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;
import java.util.ArrayList;
import java.util.List;

public class OrderCalculator {

    private final int visitDate;
    private final Order order;
    private final List<Discount> discounts;

    public OrderCalculator(final int visitDate, final Order order, final List<Discount> discounts) {
        this.visitDate = visitDate;
        this.order = order;
        this.discounts = discounts;
    }

    public int calculateTotalOrderAmount() {
        return order.calculateTotalOrderAmount();
    }

    public List<DiscountResult> calculateDiscountDetails() {
        List<DiscountResult> discountResults = new ArrayList<>();
        for (Discount discount : discounts) {
            if (discount.isApplicable(visitDate, order)) {
                DiscountResult result = discount.calculateDiscount(visitDate, order);
                discountResults.add(result);
            }
        }
        return discountResults;
    }

    public int calculateTotalDiscountBenefitAmount() {
        List<DiscountResult> discountResults = calculateDiscountDetails();
        return discountResults.stream()
            .mapToInt(DiscountResult::discountAmount)
            .sum();
    }

    public int calculateExpectedPaymentAfterDiscount() {
        return calculateTotalOrderAmount() - calculateTotalCashDiscount();
    }

    public boolean isEligibleForChampagne() {
        for (Discount discount : discounts) {
            if (!discount.calculateDiscount(visitDate, order).isCashDiscount()) {
                return discount.isApplicable(visitDate, order);
            }
        }
        return false;
    }

    private int calculateTotalCashDiscount() {
        List<DiscountResult> discountResults = calculateDiscountDetails();
        return discountResults.stream()
            .filter(DiscountResult::isCashDiscount)
            .mapToInt(DiscountResult::discountAmount)
            .sum();
    }
}
