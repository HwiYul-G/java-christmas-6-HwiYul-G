package christmas.util;

import java.util.Arrays;
import java.util.List;

public class Util {

    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private static final String COMMA = ",";
    private static final String COLON = ":";
    private static final String HYPHEN = "-";

    public static String removeSpace(final String input) {
        return input.replaceAll(SPACE, EMPTY);
    }

    public static List<String> splitByComma(final String input) {
        return Arrays.asList(Util.removeSpace(input).split(COMMA));
    }

    public static List<String> splitByHyphen(final String input) {
        return Arrays.asList(Util.removeSpace(input).split(HYPHEN));
    }

    public static List<String> splitByColon(final String input) {
        return Arrays.asList(Util.removeSpace(input).split(COLON));
    }

}
