package christmas.controller;

import christmas.model.OrderItems;
import christmas.service.CalculatorService;
import christmas.view.InputView;

public class InputController {

    private final InputView inputView;
    private final CalculatorService calculatorService;

    public InputController(final InputView inputView, final  CalculatorService calculatorService) {
        this.inputView = inputView;
        this.calculatorService = calculatorService;
    }

    public void run(){
        printStartComment();

        int visitDate = requestVisitDate();
        calculatorService.setVisitDate(visitDate);

        OrderItems orderItems = requestOrderInfo();
        calculatorService.setOrder(orderItems);
    }

    private void printStartComment() {
        inputView.printStartComment();
    }

    private int requestVisitDate() {
        do {
            try {
                return inputView.inputVisitDate().visitDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private OrderItems requestOrderInfo() {
        do {
            try {
                return inputView.inputOrderInfo();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

}
