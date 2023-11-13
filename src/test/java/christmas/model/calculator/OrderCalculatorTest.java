package christmas.model.calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.model.data.Menu;
import christmas.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderCalculatorTest {

    private OrderCalculator orderCalculator;
    private Order order;
    private List<Discount> discountCalculators;

    @BeforeEach
    void setUp() {
        order = createOrder(Map.of(
            Menu.CAESAR_SALAD, 2,
            Menu.BBQ_RIBS, 2,
            Menu.CHOCOLATE_CAKE, 3,
            Menu.ZERO_COLA, 4
        ));

        discountCalculators = new ArrayList<>();
        discountCalculators.add(new ChristmasDayDiscount());
        discountCalculators.add(new DecemberDayOfWeekDiscount());
        discountCalculators.add(new DecemberSpecialDiscount());
        discountCalculators.add(new FreeChampagneEvent());
    }

    @DisplayName("전체 혜택 금액 계산 - 모든 이벤트가 적용되는 25일")
    @Test
    void verifyTotalDiscountApplication() {
        orderCalculator = new OrderCalculator(25, order, discountCalculators);
        int expectedDiscountBenefitAmount = 35469;

        assertThat(orderCalculator.calculateTotalDiscountBenefitAmount()).isEqualTo(
            expectedDiscountBenefitAmount);
    }

    @DisplayName("할인 후 실제 현금 지불 금액 테스트 - 모든 이벤트가 적용되는 25일")
    @Test
    void verifyActualPaymentAfterDiscount() {
        orderCalculator = new OrderCalculator(25, order, discountCalculators);
        int expectedPayment = 170531;

        assertThat(orderCalculator.calculateExpectedPaymentAfterDiscount()).isEqualTo(
            expectedPayment);
    }

    @DisplayName("전체 혜택 금액 계산 - 8일 주말(메인메뉴할인), 증정 이벤트, 크리스마스디데이행사")
    @Test
    void verifyTotalDiscountApplicationOnWeekend() {
        orderCalculator = new OrderCalculator(8, order, discountCalculators);
        int expectedDiscountBenefitAmount = 2023 * 2 + 25000 + 1700;

        assertThat(orderCalculator.calculateTotalDiscountBenefitAmount()).isEqualTo(
            expectedDiscountBenefitAmount);
    }

    @DisplayName("할인 후 실제 현금 지불 금액 테스트 - 8일 주말(메인메뉴할인), 증정 이벤트, 크리스마스디데이행사 ")
    @Test
    void verifyActualPaymentAfterDiscountOnWeekend() {
        int visitDate = 8;
        orderCalculator = new OrderCalculator(visitDate, order, discountCalculators);
        int expectedPayment = 181000 - 2023 * 2 - 1700;

        assertThat(orderCalculator.calculateExpectedPaymentAfterDiscount()).isEqualTo(
            expectedPayment);
    }

    private Order createOrder(Map<Menu, Integer> orderItems) {
        return new Order(orderItems);
    }

}