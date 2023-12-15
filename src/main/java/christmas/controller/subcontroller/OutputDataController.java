package christmas.controller.subcontroller;

import christmas.controller.status.ApplicationStatus;
import christmas.domain.data.EventBadge;
import christmas.domain.data.Menu;
import christmas.domain.repository.InputDataRepository;
import christmas.domain.repository.OutputDataRepository;
import christmas.domain.service.ChristmasEvent;
import christmas.domain.service.DayEvent;
import christmas.domain.service.Event;
import christmas.domain.service.GiftEvent;
import christmas.domain.service.SpecialEvent;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class OutputDataController implements Controllable {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputDataRepository inputDataRepository;
    private final OutputDataRepository outputDataRepository;
    private final List<Event> EventService = new ArrayList<>();

    public OutputDataController(InputView inputView, OutputView outputView,
        InputDataRepository inputDataRepository, OutputDataRepository outputDataRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputDataRepository = inputDataRepository;
        this.outputDataRepository = outputDataRepository;
        initializeService();
    }

    private void initializeService() {
        EventService.add(new ChristmasEvent());
        EventService.add(new DayEvent());
        EventService.add(new SpecialEvent());
        EventService.add(new GiftEvent());
    }

    @Override
    public ApplicationStatus run() {
        processEvents();
        printBasicInfo();
        printEventInfo();
        printMoneyInfo();
        return ApplicationStatus.EXIT;
    }

    private void processEvents() {
        for (Event event : EventService) {
            outputDataRepository.addEventResult(
                event.calculateEventResult(inputDataRepository.getVisitDate(),
                    inputDataRepository.getOrders()));
        }
    }

    private void printBasicInfo() {
        outputView.printBenefitPreview(inputDataRepository.getVisitDate()); // Date를 Repo에서 가져와야함
        outputView.printOrderMenu(inputDataRepository.getOrders()); // Orders를 Repo에서 가져와야함
        outputView.printBeforeBenefitTotalPrice(
            inputDataRepository.getOrders().calculateTotalPrice()); // Orders를 Repo에서 가져와야함
    }

    private void printEventInfo() {
        int giftQuantity = 1;
        if(inputDataRepository.getOrders().calculateTotalPrice() < 120_000){
            giftQuantity = 0;
        }
        outputView.printGiftMenu(Menu.CHAMPAGNE, giftQuantity); // 증정 메뉴 정보(이름, 개수)를 Repo에서 가져와야함
        outputView.printEvent(outputDataRepository.eventResults()); // 이벤트 정보(이름, 할인액)를 Repo에서 가져와야함
    }

    private void printMoneyInfo() {
        int totalPrice = inputDataRepository.getOrders().calculateTotalPrice();
        outputView.printTotalBenefit(
            outputDataRepository.getTotalBenefit()); // totalBenefit을 Repo에서 가져와야함
        outputView.printExpectedPay(
            totalPrice - outputDataRepository.getTotalBenefit()); // expectedPay를 Repo에서 가져와야함
        outputView.printEventBadge(EventBadge.of(totalPrice)); // EventBadge 정보를 REpo에서가져와야함
    }


}
