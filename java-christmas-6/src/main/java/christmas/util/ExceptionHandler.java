package christmas.util;

public class ExceptionHandler {
    public static IllegalArgumentException exception(String _message) {
        var message = String.format("%s %s", christmas.Constant.ERROR_PREFIX, _message);
        return new IllegalArgumentException(message.trim());
    }
}
