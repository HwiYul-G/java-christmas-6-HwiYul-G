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

class ChampagneComplimentaryEventTest {

    private DiscountCalculator discountCalculator;
    private Order order;

    @BeforeEach
    void setUp() {
        discountCalculator = new ChampagneComplimentaryEvent();

        Map<Menu, Integer> orderItems = new HashMap<>();
        orderItems.put(Menu.BBQ_RIBS, 2);
        orderItems.put(Menu.CAESAR_SALAD, 2);
        orderItems.put(Menu.ZERO_COLA, 4);
        orderItems.put(Menu.CHOCOLATE_CAKE, 3);
        order = new Order(orderItems);
    }

    @DisplayName("12만원 이상 이면 샴페인 증정 이벤트 대상이다.")
    @Test
    void verifyEligibilityForChampagneEvent() {
        assertThat(discountCalculator.isApplicable(3, order)).isTrue();
    }

    @DisplayName("샴페인 증정 결과 확인")
    @Test
    void verifyChampagneEventDiscountCalculation() {
        assertThat(discountCalculator.calculateDiscount(3, order))
            .isEqualTo(new DiscountResult("증정 이벤트", 25000, false));
    }

}