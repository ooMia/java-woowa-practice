package vendingmachine;

import vendingmachine.util.ExceptionUtil;

public enum ErrorCode {
    // SCOPE_(REASON)
    
    // 37 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
    INPUT_ITEM_PRICE_OVER_100("상품 가격은 100원부터 시작한다,", Constant.INPUT_EXCEPTION),
    INPUT_ITEM_PRICE_MOD_10("상품 가격은 10원으로 나누어떨어져야 한다.", Constant.INPUT_EXCEPTION),

    // 76 "금액은 숫자여야 합니다."
    INPUT_BALANCE_INVALID("금액은 숫자여야 합니다.", Constant.INPUT_EXCEPTION),

    INPUT_INVALID("유효하지 않은 입력 값입니다.", Constant.INPUT_EXCEPTION),
    ;

    private final String message;
    private final Class<? extends RuntimeException> exceptionClass;

    ErrorCode(String message, Class<? extends RuntimeException> exceptionClass) {
        this.message = message;
        this.exceptionClass = exceptionClass;
    }

    public RuntimeException exception() {
        return ExceptionUtil.instantiate(exceptionClass, message);
    }

    public RuntimeException exception(String detail) {
        return ExceptionUtil.instantiate(exceptionClass, message + ": " + detail);
    }
}
