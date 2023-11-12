package christmas.model.calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.model.DiscountResult;
import christmas.model.Order;
import christmas.model.data.Menu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DecemberSpecialDiscountTest {

    private DiscountCalculator discountCalculator;
    private Order order;

    @BeforeEach
    void setUp() {
        discountCalculator = new DecemberSpecialDiscount();

        Map<Menu, Integer> orderItems = new HashMap<>();
        orderItems.put(Menu.BBQ_RIBS, 2);
        orderItems.put(Menu.CAESAR_SALAD, 2);
        orderItems.put(Menu.ZERO_COLA, 4);
        orderItems.put(Menu.CHOCOLATE_CAKE, 3);
        order = new Order(orderItems);
    }

    @DisplayName("스페셜 데이에는 1000원 할인이 적용된다.")
    @Test
    void verifySpecialDayDiscount() {
        int visitDate = 3;
        assertThat(discountCalculator.calculateDiscount(visitDate,order)).isEqualTo(
            new DiscountResult("특별 할인",1000, true));
    }

}