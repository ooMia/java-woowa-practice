package baseball.model.in;

import baseball.Constant;
import baseball.ErrorCode;

public record ExitCode(int code) {
    public ExitCode {
        if (!isCaseDefined(code))
            throw ErrorCode.INPUT_CASE_CANNOT_HANDLE.exception();
    }

    private static boolean isCaseDefined(int n) {
        if (n == Constant.기호_게임_재시작) return true;
        if (n == Constant.기호_게임_종료) return true;
        return false;
    }
}
