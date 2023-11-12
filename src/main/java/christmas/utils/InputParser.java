package christmas.utils;

import christmas.model.OrderItem;
import christmas.model.OrderItems;
import christmas.model.VisitDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    public static VisitDate parseToVisitDate(String input) {
        try {
            int parsedInput = Integer.parseInt(input);
            return new VisitDate(parsedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

    public static OrderItems parseToOrderItems(String input) {
        List<OrderItem> orderItemInfos = Arrays.stream(input.split(","))
            .map(item -> {
                String[] parts = item.split("-");
                String menuName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                return new OrderItem(menuName, quantity);
            })
            .collect(Collectors.toList());
        return new OrderItems(orderItemInfos);
    }


}
