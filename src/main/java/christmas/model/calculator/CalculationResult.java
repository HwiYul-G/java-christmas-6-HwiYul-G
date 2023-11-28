package christmas.model.calculator;

import christmas.model.DiscountResult;
import christmas.model.OrderItems;
import java.util.List;

public record CalculationResult(
    int totalOrderAmount,
    List<DiscountResult> discountDetails,
    int totalDiscountAmount,
    int expectedPayment,
    boolean eligibleForChampagne) {

}
