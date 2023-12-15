package christmas.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;

public interface Event {

    public EventResult calculateEventResult(int date, Orders orders);

}
