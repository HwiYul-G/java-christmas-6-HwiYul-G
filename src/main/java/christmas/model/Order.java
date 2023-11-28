package christmas.model;

import christmas.model.data.Menu;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public OrderItems toOrderItems(){
        List<OrderItem> items = orderItems.entrySet().stream()
            .map(entry -> new OrderItem(entry.getKey().getFoodName(), entry.getValue()))
            .collect(Collectors.toList());

        return new OrderItems(items);
    }
}
