package christmas.util;

import java.util.StringJoiner;

public class ExceptionHandler {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static IllegalArgumentException exception(String... messages) {
        StringJoiner joiner = new StringJoiner(" ", ERROR_PREFIX, "");
        joiner.setEmptyValue("[ERROR] 처리되지 않은 오류가 발생했습니다.");
        for (String message : messages) joiner.add(message);
        return new IllegalArgumentException(joiner.toString());
    }
}
