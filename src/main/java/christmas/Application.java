package christmas;

import christmas.controller.CalculatorController;
import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.model.OrderItems;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        InputController inputController = new InputController(inputView);
        OutputController outputController = new OutputController(outputView);

        inputController.printStartComment();

        int visitDate = inputController.requestVisitDate();
        OrderItems orderItems = inputController.requestOrderInfo();

        CalculatorController calculatorController = new CalculatorController(visitDate, orderItems);

        outputController.printOrderMenu(orderItems);
        outputController.printTotalOrderAmountBeforeDiscount(calculatorController.calculateTotalOrderAmount());
        outputController.printGiftMenu(calculatorController.isEligibleForChampagne());
        outputController.printBenefitDetails(calculatorController.calculateDiscountDetails());
        outputController.printTotalBenefitAmount(calculatorController.calculateTotalDiscountBenefitAmount());
        outputController.printExpectedPaymentAfterDiscount(calculatorController.calculateExpectedPaymentAfterDiscount());
        outputController.printEventBadge(calculatorController.calculateTotalDiscountBenefitAmount());

    }
}
