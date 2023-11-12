package christmas.model;

import christmas.model.data.Menu;
import christmas.utils.Constants;
import java.util.Map;

public record Order(Map<Menu, Integer> orderItems) {

    public int calculateTotalOrderAmount() {
        return orderItems().entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }

    public boolean isEventTarget() {
        return calculateTotalOrderAmount() >= Constants.EVENT_ELIGIBILITY_THRESHOLD_PRICE;
    }


}
