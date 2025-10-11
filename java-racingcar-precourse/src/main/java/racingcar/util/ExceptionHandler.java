package racingcar.util;

public final class ExceptionHandler {
    private static String ERROR_PREFIX = "[ERROR]";

    private ExceptionHandler() {
    }

    public static void setErrorPrefix(String prefix) {
        ERROR_PREFIX = prefix;
    }

    public static RuntimeException exception(String message) {
        return new IllegalArgumentException(ERROR_PREFIX + " " + message);
    }

    public static <T> T tryUntilValid(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (RuntimeException e) {
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
