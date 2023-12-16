package christmas.controller;

import christmas.controller.status.ApplicationStatus;
import christmas.controller.subcontroller.Controllable;
import christmas.controller.subcontroller.InputDataController;
import christmas.controller.subcontroller.OutputDataController;
import christmas.domain.service.MainService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MainService mainService;
    private final Map<ApplicationStatus, Controllable> controllers = new EnumMap<>(
        ApplicationStatus.class);

    public MainController(final InputView inputView, final OutputView outputView,
        final MainService mainService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.mainService = mainService;
        initializeControllers();
    }

    private void initializeControllers() {
        controllers.put(ApplicationStatus.INPUT_DATA, new InputDataController(inputView, outputView, mainService));
        controllers.put(ApplicationStatus.OUTPUT_DATA, new OutputDataController(inputView, outputView, mainService));
    }

    public void run() {
        ApplicationStatus status = play(ApplicationStatus.INPUT_DATA);
        while (status.runnable()) {
            status = play(status);
        }
    }

    public ApplicationStatus play(final ApplicationStatus applicationStatus) {
        try {
            return controllers.get(applicationStatus).run();
        } catch (NullPointerException e) {
            return ApplicationStatus.EXIT;
        }
    }

}
