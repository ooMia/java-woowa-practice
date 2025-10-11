package racingcar;

import racingcar.util.ExceptionHandler;

public final class Constant {
    // 잘못된 입력 // #L47
    public static final Class<IllegalArgumentException> 잘못된_입력_예외 = IllegalArgumentException.class;
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionHandler.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }

    // public static final ;// #L
    public static final String 자동차_이름_구분자_패턴 = "\\s*,\\s*"; // #L42
    public static final int 자동차_이름_길이_제한 = 5; // 5 이하 // #L42

    // 0에서 9 사이에서 무작위 값에 대해
    // 값이 4 이상일 경우, 자동차가 전진한다. // #L44
    public static final int MIN_VALUE_FORWARD = 0;
    public static final int MAX_VALUE_FORWARD = 9;
    public static final int STANDARD_VALUE_FORWARD = 4;

    public static final String 우승자_구분자 = ", "; // #L46, 84(공백 필요)

}
