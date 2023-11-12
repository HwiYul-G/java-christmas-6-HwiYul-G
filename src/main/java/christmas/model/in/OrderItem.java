package christmas.model.in;

import christmas.model.Menu;
import christmas.utils.Constants;
import christmas.utils.ExceptionMessage;

public record OrderItem(String menuName, int quantity) {

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
        if (quantity < Constants.MINIMUM_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
