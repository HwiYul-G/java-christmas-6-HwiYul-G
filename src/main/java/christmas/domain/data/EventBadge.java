package christmas.domain.data;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NO_BADGE("없음", 0);
    private final String name;
    private final int basePrice;
    EventBadge(String name, int basePrice){
        this.name = name;
        this.basePrice = basePrice;
    }

    // 주문 전체 가격을 입력 받아서 배지를 반환하는 메서드
    public static EventBadge of(int totalPrice){
        return Arrays.stream(EventBadge.values())
            .filter(eventBadge -> eventBadge.basePrice <= totalPrice)
            .max(Comparator.comparingInt(eventBadge -> eventBadge.basePrice))
            .orElse(NO_BADGE);
    }
}
