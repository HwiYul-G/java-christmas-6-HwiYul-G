package christmas.controller.subcontroller;

import christmas.controller.status.ApplicationStatus;
import christmas.controller.status.UserStatus;
import christmas.domain.Orders;
import christmas.domain.service.MainService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class InputDataController implements Controllable {

    private final InputView inputView;
    private final OutputView outputView;
    private final MainService mainService;

    public InputDataController(InputView inputView, OutputView outputView,
        MainService mainService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.mainService = mainService;
    }

    @Override
    public ApplicationStatus run() {
        outputView.printStart();

        int visitDate = inputView.inputVisitDate(); // 사용자한테 입력받은 이 상태를 Repository에 저장해야함
        UserStatus.getInstance().setVisitDate(visitDate); // 2번 방식 이게 맞는 것? -> NPE 발생 안하나?

        Orders orders = inputView.inputOrders(); // 사용자한테 입력받은 이 상태를 Repository에 저장해야함
        UserStatus.getInstance().setOrders(orders); // 2번 방식 이게 맞는 것? -> NPE 발생 안하나?

        return ApplicationStatus.OUTPUT_DATA;
    }
}
