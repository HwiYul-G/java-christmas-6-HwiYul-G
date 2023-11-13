package christmas.model;

import christmas.model.data.Menu;
import christmas.utils.ExceptionMessage;

public record OrderItem(String menuName, int quantity) {

    private static final int MINIMUM_ORDER_QUANTITY = 1;

    public OrderItem {
        validateMenuExistence(menuName);
        validateQuantity(quantity);
    }

    private void validateMenuExistence(final String menuName) {
        try {
            Menu.getMenuByName(menuName);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateQuantity(final int quantity) {
        if (quantity < MINIMUM_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
