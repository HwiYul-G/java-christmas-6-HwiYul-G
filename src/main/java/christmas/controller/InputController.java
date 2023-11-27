package christmas.controller;

import christmas.model.OrderItems;
import christmas.view.InputView;

public class InputController {

    private final InputView inputView;

    public InputController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void printStartComment() {
        inputView.printStartComment();
    }

    public int requestVisitDate() {
        do {
            try {
                return inputView.inputVisitDate().visitDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public OrderItems requestOrderInfo() {
        do {
            try {
                return inputView.inputOrderInfo();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

}
