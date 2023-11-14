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

class DecemberDayOfWeekDiscountTest {

    private Discount discountCalculator;
    private Order order;

    @BeforeEach
    void setUp() {
        discountCalculator = new DecemberDayOfWeekDiscount();

        Map<Menu, Integer> orderItems = new HashMap<>();
        orderItems.put(Menu.BBQ_RIBS, 2);
        orderItems.put(Menu.CAESAR_SALAD, 2);
        orderItems.put(Menu.ZERO_COLA, 4);
        orderItems.put(Menu.CHOCOLATE_CAKE, 3);
        order = new Order(orderItems);
    }

    @DisplayName("평일은 디저트 메뉴 할인이 적용된다.")
    @Test
    void verifyWeekdayDiscountOnDessertMenu() {
        int visitDate = 4;

        int expectedWeekdayDiscount = 2023 * 3;

        assertThat(discountCalculator.calculateDiscount(visitDate, order)).isEqualTo(
            new DiscountResult("평일 할인", expectedWeekdayDiscount, true));
    }

    @DisplayName("주말은 메인 메뉴 할인이 적용된다.")
    @Test
    void verifyWeekendDiscountOnMainMenu() {
        int visitDate = 9;

        int expectedWeekdayDiscount = 2023 * 2;

        assertThat(discountCalculator.calculateDiscount(visitDate, order)).isEqualTo(
            new DiscountResult("주말 할인", expectedWeekdayDiscount, true));
    }

}