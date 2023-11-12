package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.OrderItems;
import christmas.model.VisitDate;
import christmas.utils.InputParser;
import christmas.utils.InputValidator;

public class InputView {

    private enum ConsoleMessage {
        START_COMMENT("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
        INPUT_VISIT_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        INPUT_ORDER_INFO("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        ConsoleMessage(String message) {
            this.message = message;
        }
    }

    public void printStartComment() {
        System.out.println(ConsoleMessage.START_COMMENT.message);
    }

    public VisitDate inputVisitDate() {
        System.out.println(ConsoleMessage.INPUT_VISIT_DAY.message);
        String input = Console.readLine();
        return InputParser.parseToVisitDate(input);
    }

    public OrderItems inputOrderInfo() {
        System.out.println(ConsoleMessage.INPUT_ORDER_INFO.message);
        String input = Console.readLine();
        InputValidator.validateInputOderFormat(input);
        return InputParser.parseToOrderItems(input);
    }

}
