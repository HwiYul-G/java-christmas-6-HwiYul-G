package christmas.controller;

import christmas.controller.status.ApplicationStatus;
import christmas.controller.subcontroller.Controllable;
import christmas.controller.subcontroller.InputDataController;
import christmas.controller.subcontroller.OutputDataController;
import christmas.domain.repository.InputDataRepository;
import christmas.domain.repository.OutputDataRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputDataRepository inputDataRepository;
    private final OutputDataRepository outputDataRepository;
    private final Map<ApplicationStatus, Controllable> controllers = new EnumMap<>(
        ApplicationStatus.class);

    public MainController(final InputView inputView, final OutputView outputView,
        final InputDataRepository inputDataRepository, final OutputDataRepository outputDataRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputDataRepository = inputDataRepository;
        this.outputDataRepository = outputDataRepository;
        initializeControllers();
    }

    private void initializeControllers() {
        controllers.put(ApplicationStatus.INPUT_DATA, new InputDataController(inputView, outputView, inputDataRepository));
        controllers.put(ApplicationStatus.OUTPUT_DATA, new OutputDataController(inputView, outputView, inputDataRepository, outputDataRepository));
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
