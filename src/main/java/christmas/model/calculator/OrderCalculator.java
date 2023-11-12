package christmas.model.calculator;

import christmas.model.Order;
import christmas.model.DiscountResult;
import christmas.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class OrderCalculator {

    private final int visitDate;
    private final Order order;
    private final List<DiscountCalculator> discountCalculators;

    public OrderCalculator(final int visitDate, final Order order, final List<DiscountCalculator> discountCalculators) {
        this.visitDate = visitDate;
        this.order = order;
        this.discountCalculators = discountCalculators;
    }

    public int calculateTotalOrderAmount() {
        return order.calculateTotalOrderAmount();
    }

    public List<DiscountResult> calculateDiscountDetails() {
        List<DiscountResult> discountResults = new ArrayList<>();
        for (DiscountCalculator discountCalculator : discountCalculators) {
            if (discountCalculator.isApplicable(visitDate, order)) {
                DiscountResult result = discountCalculator.calculateDiscount(visitDate, order);
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

    public boolean isEligibleForChampagne(){
        return order.calculateTotalOrderAmount() >= Constants.SHAMPAGNE_EVENT_THRESHOLD_PRICE;
    }

    private int calculateTotalCashDiscount() {
        List<DiscountResult> discountResults = calculateDiscountDetails();
        return discountResults.stream()
            .filter(DiscountResult::isCashDiscount)
            .mapToInt(DiscountResult::discountAmount)
            .sum();
    }
}
