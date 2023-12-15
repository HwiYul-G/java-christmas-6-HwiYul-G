package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.util.InputValidator;

public class InputView {

    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public int inputVisitDate() {
        try {
            System.out.printf(Message.INPUT_VISIT_DATE.message);
            String input = Console.readLine();
            return VisitDate.of(input).visitDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputVisitDate();
        }
    }

    public Orders inputOrders() {
        try {
            System.out.printf(Message.INPUT_ORDER_MENU.message);
            String input = Console.readLine();
            return Orders.of(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputOrders();
        }
    }


    private enum Message {
        INPUT_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n"),
        INPUT_ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n");
        private final String message;

        Message(final String message) {
            this.message = message;
        }
    }


}