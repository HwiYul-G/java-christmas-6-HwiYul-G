package christmas.model;

import christmas.utils.Constants;
import christmas.utils.ExceptionMessage;
import java.util.Map;

public record Order(Map<Menu, Integer> orderItems) {

    public Order {
        validate(orderItems);
    }

    public int calculateTotalOrderAmount() {
        return orderItems().entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }


    private void validate(final Map<Menu, Integer> orderItems) {
        validateTotalMenuCount(orderItems);
        validateBeverageNotOnlyOrder(orderItems);
    }

    private void validateTotalMenuCount(final Map<Menu, Integer> orderItems) {
        int totalItemCount = orderItems.values().stream()
            .mapToInt(Integer::intValue).sum();
        if (totalItemCount < Constants.MINIMUM_ORDER_QUANTITY
            || totalItemCount > Constants.MAXIMUM_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateBeverageNotOnlyOrder(final Map<Menu, Integer> orderItems) {
        boolean allBeverages = orderItems.keySet().stream().allMatch(Menu::isBeverage);
        if (allBeverages) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }


}
