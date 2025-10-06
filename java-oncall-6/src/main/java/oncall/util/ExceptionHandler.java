package oncall.util;

public class ExceptionHandler {
    public static IllegalArgumentException exception(String _message) {
        var message = String.format("%s %s", oncall.Constant.ERROR_PREFIX, _message);
        return new IllegalArgumentException(message.trim());
    }

    public static IllegalStateException assertion(String _message) {
        var message = String.format("%s %s", oncall.Constant.ERROR_PREFIX, _message);
        return new IllegalStateException(message.trim());
    }

    public static <T> T tryUntilValid(java.util.function.Supplier<T> func) {
        while (true) {
            try {
                return func.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
