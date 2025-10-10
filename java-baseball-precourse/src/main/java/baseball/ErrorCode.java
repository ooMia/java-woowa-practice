package baseball;

import baseball.util.ExceptionHandler;

public enum ErrorCode {
    // SCOPE_(REASON)
    INPUT_GUESS_LENGTH(
            String.format("입력의 문자열의 길이는 반드시 %s이어야 합니다.", Constant.자리_수_제한),
            Constant.예외_클래스_잘못된_입력),
    INPUT_GUESS_OUT_OF_BOUND(
            String.format("%s 이상 %s 이하의 정수 문자만 입력 가능합니다.", Constant.각_자리_최솟값, Constant.각_자리_최댓값),
            Constant.예외_클래스_잘못된_입력),
    INPUT_GUESS_DUPLICATE("한 입력에 동일한 숫자가 두 개 이상 포함될 수 없습니다.", Constant.예외_클래스_잘못된_입력),

    INPUT_EXIT_CODE_NOT_IN_CASE(
            String.format("%s 또는 %s만 입력 가능합니다.", Constant.기호_게임_재시작, Constant.기호_게임_종료),
            Constant.예외_클래스_잘못된_입력),

    INPUT_INVALID("유효하지 않은 입력 값입니다.", Constant.예외_클래스_잘못된_입력) // fallback 용도
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
