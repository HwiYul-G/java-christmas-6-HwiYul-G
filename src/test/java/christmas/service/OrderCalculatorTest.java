package christmas.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.utils.Constants;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderCalculatorTest {

    private OrderCalculator orderCalculator;
    private Order order;

    @BeforeEach
    void setUp() {
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
        orderCalculator = new OrderCalculator(new VisitDate(3), order);

        int expectedChristMasDiscountAmount = 1200;

        assertThat(orderCalculator.calculateChristmasDayDiscountAmount()).isEqualTo(
            expectedChristMasDiscountAmount);
    }

    @DisplayName("크리스마스디데이 할인 행사 확인 - 이벤트 기간이 아닌 경우")
    @Test
    void verifyChristmasDayDiscountOnNOEventDay() {
        orderCalculator = new OrderCalculator(new VisitDate(30), order);

        int expectedChristMasDiscountAmount = 0;

        assertThat(orderCalculator.calculateChristmasDayDiscountAmount()).isEqualTo(
            expectedChristMasDiscountAmount);
    }

    @DisplayName("평일은 디저트 메뉴 할인이 적용된다.")
    @Test
    void verifyWeekdayDiscountOnDessertMenu() {
        orderCalculator = new OrderCalculator(new VisitDate(4), order);
        int expectedWeekdayDiscount = Constants.DAY_OF_WEEK_DISCOUNT_AMOUNT * 3;

        assertThat(orderCalculator.calculateDecemberDayOfWeekDiscountAmount()).isEqualTo(
            expectedWeekdayDiscount);
    }

    @DisplayName("주말은 메인 메뉴 할인이 적용된다.")
    @Test
    void verifyWeekendDiscountOnMainMenu() {
        orderCalculator = new OrderCalculator(new VisitDate(9), order);
        int expectedWeekdayDiscount = Constants.DAY_OF_WEEK_DISCOUNT_AMOUNT * 2;

        assertThat(orderCalculator.calculateDecemberDayOfWeekDiscountAmount()).isEqualTo(
            expectedWeekdayDiscount);
    }

    @DisplayName("스페셜 데이에는 1000원 할인이 적용된다.")
    @Test
    void verifySpecialDayDiscount() {
        orderCalculator = new OrderCalculator(new VisitDate(3), order);

        assertThat(orderCalculator.calculateDecemberSpecialDiscountAmount()).isEqualTo(
            Constants.SPECIAL_DISCOUNT_AMOUNT);
    }

    @DisplayName("12만원이 넘으면 샴페인 증정 이벤트 대상이다.")
    @Test
    void verifyEligibilityForChampagneEvent() {
        orderCalculator = new OrderCalculator(new VisitDate(1), order);

        assertThat(orderCalculator.isEligibleForChampagneComplimentaryEvent()).isTrue();
    }

    @DisplayName("전체 혜택 금액 계산")
    @Test
    void verifyTotalDiscountApplication() {
        orderCalculator = new OrderCalculator(new VisitDate(25), order);
        int expectedDiscountBenefitAmount = 35469;

        assertThat(orderCalculator.calculateTotalDiscountBenefitAmount()).isEqualTo(
            expectedDiscountBenefitAmount);
    }

    @DisplayName("할인 후 실제 현금 지불 금액 테스트")
    @Test
    void verifyActualPaymentAfterDiscount() {
        orderCalculator = new OrderCalculator(new VisitDate(25), order);
        int expectedPayment = 170531;

        assertThat(orderCalculator.calculateExpectedPaymentAfterDiscount()).isEqualTo(
            expectedPayment);
    }

}