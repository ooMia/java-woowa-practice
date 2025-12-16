package subway.util;

/**
 * 런타임 예외 인스턴스 생성을 보조하고, 생성 시 메시지에 접두사를 추가하는 유틸 클래스.
 *
 * @see Problem
 * @see ExceptionHandler
 */
@SuppressWarnings("unused")
public final class ExceptionUtils {
    private static String prefix = "[ERROR] ";

    private ExceptionUtils() {
        /* no-op */
    }

    public static void setPrefix(String prefix) {
        ExceptionUtils.prefix = prefix;
    }

    /* ExceptionBuilder proxy helper methods */

    public static RuntimeException exception(Class<? extends RuntimeException> clazz) {
        return ExceptionBuilder.of(clazz).build();
    }

    public static RuntimeException exception(Class<? extends RuntimeException> clazz, String message) {
        return ExceptionBuilder.of(clazz).setMessage(message).build();
    }

    public static RuntimeException exception(Class<? extends RuntimeException> clazz, Throwable cause) {
        return ExceptionBuilder.of(clazz).setCause(cause).build();
    }

    public static RuntimeException exception(Class<? extends RuntimeException> clazz, String message, Throwable cause) {
        return ExceptionBuilder.of(clazz).setMessage(message).setCause(cause).build();
    }

    /**
     * @implNote {@code enum}을 활용하면 상황에 맞는 예외 상황을 쉽게 나열할 수 있다.<pre>{@code
     * enum SomeExceptions implements Problem {
     *     INVALID_ARGUMENTS("Invalid Arguments"),
     *     UNSUPPORTED_OPERATION(UnsupportedOperationException.class),
     *     ;
     *
     *     // ... fields and constructors
     * }
     * }</pre>
     * 조건 확인 기반 예외에는 {@link Problem#throwsIf(boolean)},<br> 그 외 일반적인 인스턴스 생성은 {@link Problem#exception()}을 사용한다.
     * {@snippet :
     * try {
     *     SomeExceptions.INVALID_ARGUMENTS.throwsIf(true); // @highlight substring="throwsIf"
     * } catch (IllegalArgumentException e) {
     *     throw SomeExceptions.UNSUPPORTED_OPERATION.exception(); // @highlight substring="exception"
     * }
     *}
     * @see Problem#exception(Throwable)
     * @see ExceptionBuilder
     */
    public interface Problem {

        String message();

        default Class<? extends RuntimeException> clazz() {
            return IllegalArgumentException.class;
        }

        default RuntimeException exception() {
            return ExceptionBuilder.of(clazz()).setMessage(message()).build();
        }

        default RuntimeException exception(Throwable cause) {
            return ExceptionBuilder.of(clazz()).setMessage(message()).setCause(cause).build();
        }

        default void throwsIf(boolean condition) {
            if (condition) {
                throw this.exception();
            }
        }
    }

    /**
     * {@link IllegalArgumentException}을 던지는 함수의 예외 처리를 보조하는 헬퍼 클래스.<br> 다음은 이를 사용하는 간단한 예시이다.
     * {@snippet :
     * var handler = new ExceptionHandler(new Console() {});
     * TargetSupplier<String> logic = () -> "doSomething";
     *
     * var resultWhenNotThrown = handler.throwsIfInvalid(logic);
     * var resultWhenSucceed = handler.tryUntilValid(logic);
     *}
     */
    @SuppressWarnings("ClassCanBeRecord")
    public static class ExceptionHandler {
        private final Console console;

        public ExceptionHandler(Console console) {
            this.console = console;
        }

        /**
         * 잘못된 동작에 {@link IllegalArgumentException}을 던지는 로직 {@link TargetSupplier supplier}에 대해,<br> 예외를 잡으면 정의된 콘솔을 통해
         * 메시지를 출력하고, re-throw하는 동작을 구현한 헬퍼 메서드.
         *
         * @param supplier 실행 로직 (lambda)
         * @param <T>      파라미터 {@link TargetSupplier supplier}의 반환형
         * @return 로직의 실행 결과. 예외가 발생하지 않은 경우에만 반환된다.
         */
        public <T> T throwsIfInvalid(TargetSupplier<T> supplier) throws IllegalArgumentException {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                console.printLine(e.getMessage());
                throw e;
            }
        }

        /**
         * 잘못된 동작에 {@link IllegalArgumentException}을 던지는 로직 {@link TargetSupplier supplier}에 대해,<br> 결과 값이 항상 기대한 조건 하에
         * 반환되었음을 보장하는 동작을 구현하기 위한 헬퍼 메서드.<br> 예외를 잡으면 정의된 콘솔을 통해 메시지를 출력하고 다시 로직을 실행한다.
         *
         * @param supplier 실행 로직 (lambda)
         * @param <T>      파라미터 {@link TargetSupplier supplier}의 반환형
         * @return 로직의 실행 결과
         * @apiNote 로직이 항상 {@link IllegalArgumentException}을 던지면 프로그램은 무한 루프에 빠진다.<br> 예외를 던지지 않아 결과를 반환하거나, 잡을 수 없는 예외를
         * 던지면 루프를 벗어나게 된다.
         */
        public <T> T tryUntilValid(TargetSupplier<T> supplier) {
            while (true) {
                try {
                    return supplier.get();
                } catch (IllegalArgumentException e) {
                    console.printLine(e.getMessage());
                }
            }
        }

        public void tryUntilValid(Runnable runnable) {
            while (true) {
                try {
                    runnable.run();
                    break;
                } catch (IllegalArgumentException e) {
                    console.printLine(e.getMessage());
                }
            }
        }

        @FunctionalInterface
        public interface TargetSupplier<T> {
            T get() throws IllegalArgumentException;
        }
    }

    /**
     * 유틸 패키지 내부에서 한정적으로 사용하기 위해 작성된 리플렉션 기반 예외 생성기.
     */
    static class ExceptionBuilder {

        private final Class<? extends RuntimeException> clazz;
        private String message;
        private Throwable cause;

        private ExceptionBuilder(Class<? extends RuntimeException> clazz) {
            this.clazz = clazz;
        }

        static ExceptionBuilder of(Class<? extends RuntimeException> clazz) throws NullPointerException {
            return new ExceptionBuilder(clazz);
        }

        private static RuntimeException tryOrThrows(ExceptionSupplier supplier) {
            try {
                return supplier.get();
            } catch (ReflectiveOperationException e) {
                throw new ExceptionBuilderException(e);
            }
        }

        ExceptionBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        ExceptionBuilder setCause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        RuntimeException build() throws ExceptionBuilderException {
            return tryOrThrows(() -> {
                if (message != null && cause != null) {
                    return clazz.getConstructor(String.class, Throwable.class).newInstance(prefix + message, cause);
                }
                if (message != null) {
                    return clazz.getConstructor(String.class).newInstance(prefix + message);
                }
                if (cause != null) {
                    return clazz.getConstructor(Throwable.class).newInstance(cause);
                }
                return clazz.getConstructor().newInstance();
            });
        }

        @FunctionalInterface
        private interface ExceptionSupplier {
            RuntimeException get() throws ReflectiveOperationException;
        }

        private static class ExceptionBuilderException extends RuntimeException {
            public ExceptionBuilderException(Throwable cause) {
                super(cause);
            }
        }
    }
}
