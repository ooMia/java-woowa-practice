package baseball;

import baseball.util.ExceptionHandler;

public enum ErrorCode {
    // SCOPE_(REASON)
    INPUT_INVALID("유효하지 않은 입력 값입니다.", IllegalArgumentException.class);

    private final String message;
    private final Class<? extends RuntimeException> exceptionClass;

    ErrorCode(String message, Class<? extends RuntimeException> exceptionClass) {
        this.message = message;
        this.exceptionClass = exceptionClass;
    }

    public RuntimeException exception() {
        return ExceptionHandler.instantiate(exceptionClass, message);
    }
}
