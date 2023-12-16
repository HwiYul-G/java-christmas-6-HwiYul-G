package christmas.controller.status;

import christmas.domain.EventResult;
import christmas.domain.Orders;
import java.util.List;

// 사용자의 상태 정보 (입력데이터인 방문날짜, Orders orders, 출력 데이터인 List<EventResult> eventResults), 기대 금액 등을 저장하는 클래스
// Singleton Pattern을 적용하여, 어디서든지 접근 가능하도록 설계
public class UserStatus {
    private static UserStatus instance;

    private int visitDate;
    private Orders orders;
    private List<EventResult> eventResults;
    private int expectedBenefit;
    private int expectedPay;

    private UserStatus() {
    }

    public static UserStatus getInstance() {
        if (instance == null) {
            instance = new UserStatus();
        }
        return instance;
    }

    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setEventResults(List<EventResult> eventResults) {
        this.eventResults = eventResults;
    }

    public void setExpectedBenefit(int expectedBenefit) {
        this.expectedBenefit = expectedBenefit;
    }

    public int getVisitDate() {
        return visitDate;
    }

    public Orders getOrders() {
        return orders;
    }

    public List<EventResult> getEventResults() {
        return eventResults;
    }

    public int getExpectedBenefit() {
        return expectedBenefit;
    }

    public int getExpectedPay() {
        return expectedPay;
    }

    public void addEventResult(EventResult eventResult) {
        eventResults.add(eventResult);
    }

    public int getTotalBenefit() {
        return eventResults.stream()
            .mapToInt(EventResult::benefitPrice)
            .sum();
    }


    public void setExpectedPay(int expectedPay) {
        this.expectedPay = expectedPay;
    }
}
