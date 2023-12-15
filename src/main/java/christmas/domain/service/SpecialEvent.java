package christmas.domain.service;

import christmas.domain.EventResult;
import christmas.domain.Orders;
import java.util.List;

public class SpecialEvent implements Event {
    private static final String EVENT_NAME = "특별 할인";
    private static final List<Integer> SPECIAL_DATES = List.of(3, 10, 17, 24, 25, 31);

    private boolean isSpecialDate(int date){
        return SPECIAL_DATES.contains(date);
    }
    private int calculateDiscountPrice(int date){
        if(isSpecialDate(date)){
            return 0;
        }
        return 1000;
    }


    @Override
    public boolean isAvailable(Orders orders) {
        return orders.isEventTarget();
    }

    @Override
    public EventResult calculateEventResult(int date, Orders orders) {
        if(!isAvailable(orders)){
            return new EventResult(EVENT_NAME,0);
        }
        int discountPrice = calculateDiscountPrice(date);
        return new EventResult(EVENT_NAME, discountPrice);
    }
}
