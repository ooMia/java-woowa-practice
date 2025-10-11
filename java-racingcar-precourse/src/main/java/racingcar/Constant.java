package racingcar;

import racingcar.util.ExceptionHandler;

public final class Constant {
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionHandler.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }
}
