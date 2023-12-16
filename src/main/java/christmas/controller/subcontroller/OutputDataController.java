package christmas.controller.subcontroller;

import christmas.controller.status.ApplicationStatus;
import christmas.controller.status.UserStatus;
import christmas.domain.EventResult;
import christmas.domain.Orders;
import christmas.domain.data.EventBadge;
import christmas.domain.data.Menu;
import christmas.domain.service.MainService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class OutputDataController implements Controllable {

    private final InputView inputView;
    private final OutputView outputView;
    private final MainService mainService;

    public OutputDataController(InputView inputView, OutputView outputView,
        MainService mainService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.mainService = mainService;
    }

    @Override
    public ApplicationStatus run() {
        processData();
        printBasicInfo();
        printEventInfo();
        printMoneyInfo();
        return ApplicationStatus.EXIT;
    }

    private void processData(){
        // service를 이용해서 데이터를 status로 집어넣기
        int visitDate = UserStatus.getInstance().getVisitDate();
        Orders orders = UserStatus.getInstance().getOrders();

        List<EventResult> eventResults = mainService.calculateEventResults(visitDate, orders);
        UserStatus.getInstance().setEventResults(eventResults);

        int expectedBenefit = mainService.calculateExpectedBenefit(eventResults);
        UserStatus.getInstance().setExpectedBenefit(expectedBenefit);


        int expectedPay = mainService.calculateExpectedPay(visitDate, orders);
        UserStatus.getInstance().setExpectedPay(expectedPay);
    }



    private void printBasicInfo() {
        outputView.printBenefitPreview(UserStatus.getInstance().getVisitDate()); // Date를 Repo에서 가져와야함
        outputView.printOrderMenu(UserStatus.getInstance().getOrders()); // Orders를 Repo에서 가져와야함
        outputView.printBeforeBenefitTotalPrice(UserStatus.getInstance().getOrders().calculateTotalPrice()); // Orders를 Repo에서 가져와야함
    }

    private void printEventInfo() {
        int giftQuantity = 1;
        if(UserStatus.getInstance().getOrders().calculateTotalPrice() < 120_000){
            giftQuantity = 0;
        }
        outputView.printGiftMenu(Menu.CHAMPAGNE, giftQuantity); // 증정 메뉴 정보(이름, 개수)를 Repo에서 가져와야함
        outputView.printEvent(UserStatus.getInstance().getEventResults()); // 이벤트 정보(이름, 할인액)를 Repo에서 가져와야함
    }

    private void printMoneyInfo() {
        int totalPrice = UserStatus.getInstance().getOrders().calculateTotalPrice();
        outputView.printTotalBenefit(UserStatus.getInstance().getTotalBenefit()); // totalBenefit을 Repo에서 가져와야함
        outputView.printExpectedPay(UserStatus.getInstance().getExpectedPay()); // expectedPay를 Repo에서 가져와야함
        outputView.printEventBadge(EventBadge.of(totalPrice)); // EventBadge 정보를 REpo에서가져와야함
    }


}
