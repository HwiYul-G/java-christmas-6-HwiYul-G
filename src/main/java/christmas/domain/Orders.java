package christmas.domain;

import christmas.domain.data.Category;
import christmas.domain.data.Menu;
import christmas.util.ExceptionMessage;
import christmas.util.Util;
import java.util.List;

public record Orders(List<Order> orders) {

    private static final int EVENT_TARGET_PRICE = 10000;
    private static final int MAX_QUANTITY = 20;

    public Orders {
        validate();
    }

    private void validate() {
        validateDuplication();
        validateQuantity();
        validateCategory();
    }

    // 중복 메뉴(Order.name)이 동일한 게 있는 경우 불가능
    private void validateDuplication() {
        long distinctCount = orders.stream()
            .map(Order::menu)
            .distinct()
            .count();
        if (distinctCount != orders.size()) {
            throw new IllegalArgumentException(
                ExceptionMessage.INVALID_ORDERS_DUPLICATION.getMessage());
        }
    }

    // 전체 주문 수량이 20개 이하만 가능하다. 검증 로직
    private void validateQuantity() {
        if (orders.stream().mapToInt(Order::quantity).sum() > MAX_QUANTITY) {
            throw new IllegalArgumentException(
                ExceptionMessage.INVALID_ORDERS_QUANTITY.getMessage());
        }
    }

    // 카테고리상 음료만 주문한 경우 오류 발생
    private void validateCategory() {
        if (orders.stream().map(Order::menu).map(Menu::getCategory)
            .allMatch(category -> category.equals(Category.DRINK))) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_CATEGORY.getMessage());
        }
    }


    // 주문 총액 계산
    public int calculateTotalPrice() {
        return orders.stream()
            .mapToInt(Order::calculatePrice)
            .sum();
    }

    // 이벤트 대상인지 확인
    public boolean isEventTarget() {
        return calculateTotalPrice() >= EVENT_TARGET_PRICE;
    }

    public static Orders of(String input) {
        // input은 메뉴이름-수량,메뉴이름-수량,메뉴이름-수량 형태로 들어온다.
        // 메뉴이름과 수량을 분리해서 Order 객체를 생성하고, Orders 객체를 생성한다.
        List<String> orderData = Util.splitByComma(input);
        return new Orders(orderData.stream()
            .map(Order::of)
            .toList());
    }

}
