package christmas.domain.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;
import java.util.List;

public class MainService {

    private final List<Event> events;

    public MainService(List<Event> events) {
        this.events = events;
    }

    // 증정 메뉴 관련

    public List<EventResult> calculateEventResults(int date, Orders orders) {
        return events.stream()
                .filter(event -> event.isAvailable(orders))
                .map(event -> event.calculateEventResult(date, orders))
                .toList();
    }

    public int calculateExpectedBenefit(List<EventResult> eventResults) {
        return eventResults.stream()
                .mapToInt(EventResult::benefitPrice)
                .sum();
    }

    public int calculateExpectedPay(int date, Orders orders) {
        return orders.calculateTotalPrice() - calculateExpectedBenefit(calculateEventResults(date, orders));
    }


}
