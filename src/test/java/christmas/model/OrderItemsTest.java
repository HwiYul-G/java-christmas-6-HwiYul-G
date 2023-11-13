package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class OrderItemsTest {

    private List<OrderItem> orderItems;

    @BeforeEach
    void setUp() {
        orderItems = new ArrayList<>();
    }

    @DisplayName("중복된 주문은 예외처리한다.")
    @Test
    void cannotDuplicationOrderMenu() {
        orderItems.add(new OrderItem("양송이수프", 2));
        orderItems.add(new OrderItem("양송이수프", 1));

        assertThatThrownBy(() -> new OrderItems(orderItems)).isInstanceOf(
            IllegalArgumentException.class);
    }


    @DisplayName("전체 메뉴 개수는 20개를 넘을 수 없다.")
    @Test
    void cannotTotalMenuOverTwenty() {
        orderItems.add(new OrderItem("초코케이크", 19));
        orderItems.add(new OrderItem("양송이수프", 10));

        assertThatThrownBy(() -> new OrderItems(orderItems)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문할 수 없다.")
    @Test
    void cannotOrderOnlyBeverage() {
        orderItems.add(new OrderItem("제로콜라", 3));

        assertThatThrownBy(() -> new OrderItems(orderItems)).isInstanceOf(
            IllegalArgumentException.class);
    }

}


