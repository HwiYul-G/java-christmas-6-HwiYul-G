package christmas.view;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printStart() {
        System.out.printf(Message.OUTPUT_START.message);
    }

    // TODO : printf로 출력하는 것을 기본으로 할 것
    private enum Message {
        OUTPUT_START("");

        private final String message;

        Message(final String message) {
            this.message = message;
        }
    }

}