package christmas.view;

public class InputView {

    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {

    }

    private enum Message {
        INPUT_("");
        private final String message;

        Message(final String message) {
            this.message = message;
        }
    }


}