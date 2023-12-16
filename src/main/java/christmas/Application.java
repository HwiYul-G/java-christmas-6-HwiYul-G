package christmas;

import christmas.controller.MainController;
import christmas.domain.service.ChristmasEvent;
import christmas.domain.service.DayEvent;
import christmas.domain.service.Event;
import christmas.domain.service.GiftEvent;
import christmas.domain.service.MainService;
import christmas.domain.service.SpecialEvent;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        List<Event> events = List.of(new ChristmasEvent(), new DayEvent(), new SpecialEvent(), new GiftEvent());
        MainService mainService = new MainService(events);

        MainController mainController = new MainController(inputView, outputView, mainService);
        mainController.run();
    }
}
