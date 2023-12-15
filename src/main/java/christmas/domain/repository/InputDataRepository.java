package christmas.domain.repository;

import christmas.domain.Orders;

public class InputDataRepository {
    private static InputDataRepository instance;
    private int visitDate;
    private Orders orders;
    private InputDataRepository() {
    }

    public static InputDataRepository getInstance() {
        if (instance == null) {
            instance = new InputDataRepository();
        }
        return instance;
    }

    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getVisitDate() {
        return visitDate;
    }

    public Orders getOrders() {
        return orders;
    }

}
