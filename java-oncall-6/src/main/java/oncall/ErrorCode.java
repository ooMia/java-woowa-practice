package oncall;

import oncall.util.ExceptionHandler;

public enum ErrorCode {
    유효하지_않은_입력("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    인자_오류("잘못된 인자가 전달되었습니다.");

    private final String reason;

    ErrorCode(String reason) {
        this.reason = reason;
    }

    public IllegalArgumentException exception() {
        return ExceptionHandler.exception(reason);
    }

    public IllegalStateException assertion() {
        return ExceptionHandler.assertion(reason);
    }
}
