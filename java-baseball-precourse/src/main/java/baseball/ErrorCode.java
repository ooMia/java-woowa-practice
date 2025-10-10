package baseball;

import baseball.util.ExceptionHandler;

public enum ErrorCode {
    // SCOPE_(REASON)
    INPUT_문자열_길이("입력의 문자열의 길이는 반드시 3이어야 합니다.", Constant.예외_클래스_잘못된_입력), // TODO Constant 적용
    INPUT_1부터_9사이_아닌_문자("1 이상 9 이하의 정수 문자만 입력 가능합니다.", Constant.예외_클래스_잘못된_입력), // TODO Constant 적용
    INPUT_중복이_존재하는_숫자들("한 입력에 동일한 숫자가 두 개 이상 포함될 수 없습니다.", Constant.예외_클래스_잘못된_입력),
    INPUT_CASE_CANNOT_HANDLE("1 또는 2만 입력 가능합니다.", Constant.예외_클래스_잘못된_입력), // TODO Constant 적용
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
