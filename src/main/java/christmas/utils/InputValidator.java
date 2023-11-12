package christmas.utils;

public class InputValidator {

    private final static String INPUT_ORDER_REGEX = "^([가-힣]+-\\d+(,\\s*[가-힣]+-\\d+)*)$";

    public static void validateInputOderFormat(String input) {
        if (!input.matches(INPUT_ORDER_REGEX)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

}
