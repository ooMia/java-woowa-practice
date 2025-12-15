package vendingmachine.util;

import vendingmachine.util.ExceptionUtils.Problem;

@SuppressWarnings("unused")
public enum GlobalExceptions implements Problem {
    INVALID_FORMAT,
    INVALID_ARGUMENTS,
    INVALID_STATE(IllegalStateException.class),
    UNSUPPORTED_OPERATION(UnsupportedOperationException.class),
    ;

    private final Class<? extends RuntimeException> clazz;

    GlobalExceptions() {
        this.clazz = Problem.super.clazz();
    }

    GlobalExceptions(Class<? extends RuntimeException> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String message() {
        return this.name();
    }

    @Override
    public Class<? extends RuntimeException> clazz() {
        return this.clazz;
    }
}
