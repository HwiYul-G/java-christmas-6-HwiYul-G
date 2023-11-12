package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {


    @DisplayName("존재 하지 않는 메뉴는 주문할 수 없다.")
    @Test
    void canNotOrderNonExistingMenu() {
        assertThatThrownBy(() -> new OrderItem("떡볶이", 2))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최소 주문은 1개 이상만 가능하다.")
    @Test
    void orderQuantityIsOverThanOne() {
        assertThatThrownBy(()->new OrderItem("양송이수프",-2))
            .isInstanceOf(IllegalArgumentException.class);
    }

}