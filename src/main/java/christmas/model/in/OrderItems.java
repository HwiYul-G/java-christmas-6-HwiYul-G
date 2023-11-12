package christmas.model.in;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.utils.Constants;
import christmas.utils.ExceptionMessage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record OrderItems(List<OrderItem> orderItems) {

    public OrderItems {
        validate(orderItems);
    }

    private void validate(final List<OrderItem> orderItems) {
        validateDuplication(orderItems);
        validateTotalMenuCount(orderItems);
        validateBeverageNotOnlyOrder(orderItems);
    }

    private void validateDuplication(final List<OrderItem> orderItems) {
        int uniqueCount = (int) orderItems.stream()
            .map(OrderItem::menuName)
            .distinct()
            .count();
        if (uniqueCount != orderItems.size()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateTotalMenuCount(final List<OrderItem> orderItems) {
        int totalItemCount = orderItems.stream()
            .mapToInt(OrderItem::quantity)
            .sum();
        if (totalItemCount > Constants.MAXIMUM_TOTAL_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateBeverageNotOnlyOrder(final List<OrderItem> orderItems) {
        boolean allBeverages = orderItems.stream()
            .map(OrderItem::menuName)
            .map(Menu::getMenuByName)
            .allMatch(Menu::isBeverage);
        if (allBeverages) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    public Order toOrder() {
        Map<Menu, Integer> orderInfo = orderItems.stream()
            .collect(Collectors.toMap(
                orderItem -> Menu.getMenuByName(orderItem.menuName()),
                OrderItem::quantity
            ));
        return new Order(orderInfo);
    }
}