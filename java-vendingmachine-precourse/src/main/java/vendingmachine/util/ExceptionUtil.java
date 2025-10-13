package vendingmachine.util;

public final class ExceptionUtil {
    private static String ERROR_PREFIX = "[ERROR]";

    private ExceptionUtil() {
    }

    public static void setErrorPrefix(String prefix) {
        ERROR_PREFIX = prefix;
    }

    public static IllegalArgumentException exception(String message) {
        return new IllegalArgumentException(ERROR_PREFIX + " " + message);
    }

    public static UnsupportedOperationException unsupported(String scope, String message) {
        return new UnsupportedOperationException(ERROR_PREFIX + " " + scope + ": " + message);
    }

    public static <T> T tryUntilValid(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static RuntimeException instantiate(
            Class<? extends RuntimeException> exceptionClass,
            String message
    ) {
        try {
            return exceptionClass
                    .getConstructor(String.class)
                    .newInstance(ERROR_PREFIX + " " + message);
        } catch (Exception e) {
            throw new RuntimeException("ExceptionHandler::instantiate", e);
        }
    }
}
