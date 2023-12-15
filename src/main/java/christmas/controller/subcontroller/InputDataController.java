package christmas.controller.subcontroller;

import christmas.controller.status.ApplicationStatus;
import christmas.domain.Orders;
import christmas.domain.repository.InputDataRepository;
import christmas.view.InputView;
import christmas.view.OutputView;

public class InputDataController implements Controllable {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputDataRepository inputDataRepository;

    public InputDataController(InputView inputView, OutputView outputView,
        InputDataRepository inputDataRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputDataRepository = inputDataRepository;
    }

    @Override
    public ApplicationStatus run() {
        outputView.printStart();

        int visitDate = inputView.inputVisitDate(); // 사용자한테 입력받은 이 상태를 Repository에 저장해야함
        inputDataRepository.setVisitDate(visitDate);

        Orders orders = inputView.inputOrders(); // 사용자한테 입력받은 이 상태를 Repository에 저장해야함
        inputDataRepository.setOrders(orders);

        return ApplicationStatus.OUTPUT_DATA;
    }
}
