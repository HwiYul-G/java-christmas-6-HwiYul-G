package christmas;

import christmas.controller.MainController;
import christmas.domain.repository.InputDataRepository;
import christmas.domain.repository.OutputDataRepository;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        InputDataRepository inputDataRepository = InputDataRepository.getInstance();
        OutputDataRepository outputDataRepository = OutputDataRepository.getInstance();

        MainController mainController = new MainController(inputView, outputView, inputDataRepository, outputDataRepository);
        mainController.run();
    }
}
