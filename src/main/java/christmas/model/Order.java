package christmas.model;

import christmas.model.data.Menu;
import java.util.Map;

public record Order(Map<Menu, Integer> orderItems) {
    private static final int EVENT_THRESHOLD_PRICE = 10_000;

    public int calculateTotalOrderAmount() {
        return orderItems().entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }

    public boolean isEventTarget() {
        return calculateTotalOrderAmount() >= EVENT_THRESHOLD_PRICE;
    }

}
