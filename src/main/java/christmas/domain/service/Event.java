package christmas.domain.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;

public interface Event {

    public boolean isAvailable(Orders orders);

    public EventResult calculateEventResult(int date, Orders orders);

}
