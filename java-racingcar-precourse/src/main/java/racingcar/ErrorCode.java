package racingcar;

import racingcar.util.ExceptionHandler;

public enum ErrorCode {
    // SCOPE_(REASON)

    // 자동차 이름 입력 String // #L42
    INPUT_자동차_이름_길이_제한("자동차 이름 길이는 5 이하여야 한다.", Constant.잘못된_입력_예외),

    // 시도 횟수 입력 int
    INPUT_횟수_수("시도 횟수는 숫자여야 한다.", Constant.잘못된_입력_예외), // #L90
    INPUT_횟수_자연수("시도 횟수는 자연수여야 한다.", Constant.잘못된_입력_예외),

    CAR_INVALID_FUEL("자동차 연료는 0 이상 9 이하의 값이어야 한다.", IllegalStateException.class),

    INPUT_INVALID("유효하지 않은 입력 값입니다.", Constant.잘못된_입력_예외) // fallback
    ;

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
