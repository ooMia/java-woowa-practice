package lotto.util;

public class ExceptionHandler {
    private static final String ERROR_PREFIX = "[ERROR]";

    public static IllegalArgumentException exception(String message) {
        return new IllegalArgumentException(String.format("%s %s", ERROR_PREFIX, message));
    }
}
