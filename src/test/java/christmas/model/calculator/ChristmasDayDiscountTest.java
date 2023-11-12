package christmas.model.calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.model.DiscountResult;
import christmas.model.Order;
import christmas.model.data.Menu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDayDiscountTest {

    private DiscountCalculator discountCalculator;
    private Order order;

    @BeforeEach
    void setUp() {
        discountCalculator = new ChristmasDayDiscount();

        Map<Menu, Integer> orderItems = new HashMap<>();
        orderItems.put(Menu.BBQ_RIBS, 2);
        orderItems.put(Menu.CAESAR_SALAD, 2);
        orderItems.put(Menu.ZERO_COLA, 4);
        orderItems.put(Menu.CHOCOLATE_CAKE, 3);
        order = new Order(orderItems);
    }

    @DisplayName("크리스마스디데이 할인 행사 확인 - 이벤트 기간")
    @Test
    void verifyChristmasDayDiscount() {
        int visitDate = 3;

        int expectedChristMasDiscountAmount = 1200;

        assertThat(discountCalculator.calculateDiscount(visitDate, order)).isEqualTo(
            new DiscountResult("크리스마스 디데이 할인", expectedChristMasDiscountAmount, true));
    }

    @DisplayName("크리스마스디데이 할인 행사 확인 - 이벤트 기간이 아닌 경우")
    @Test
    void verifyChristmasDayDiscountOnNOEventDay() {

        int visitDate = 30;

        int expectedChristMasDiscountAmount = 0;

        assertThat(discountCalculator.calculateDiscount(visitDate, order)).isEqualTo(
            new DiscountResult("크리스마스 디데이 할인", expectedChristMasDiscountAmount, true));
    }


}