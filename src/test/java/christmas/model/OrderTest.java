package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.utils.ExceptionMessage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Map<Menu, Integer> orderItems;

    @BeforeEach
    void setUp(){
        orderItems = new HashMap<>();
    }

    @DisplayName("전체 주문 금액이 올바르게 계산되는지 확인한다.")
    @Test
    void calculateTotalOrderAmountIsCorrect(){
        orderItems.put(Menu.CAESAR_SALAD, 1);
        orderItems.put(Menu.BBQ_RIBS, 2);

        Order order = new Order(orderItems);
        int excpectedOrderAmount = 116000;

        assertThat(order.calculateTotalOrderAmount()).isEqualTo(excpectedOrderAmount);
    }

    @DisplayName("전체 주문 메뉴가 20개 초과인 경우 예외가 발생한다.")
    @Test
    void orderExceedingMaxItems() {
        orderItems.put(Menu.CAESAR_SALAD, 22);

        assertThatThrownBy(() -> new Order(orderItems))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("음료만 주문한 경우 예외가 발생한다.")
    @Test
    void orderOnlyBeverage() {
        orderItems.put(Menu.ZERO_COLA, 2);

        assertThatThrownBy(() -> new Order(orderItems))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

}