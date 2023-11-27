package christmas;

import christmas.service.CalculatorService;
import christmas.controller.InputController;
import christmas.controller.OutputController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CalculatorService calculatorService = new CalculatorService();

        InputController inputController = new InputController(inputView, calculatorService);
        OutputController outputController = new OutputController(outputView, calculatorService);

        inputController.run();
        outputController.run();
    }
}
