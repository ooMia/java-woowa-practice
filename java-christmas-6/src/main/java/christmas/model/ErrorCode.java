package christmas.model;

public enum ErrorCode {
    // 방문 날짜
    DATE_NOT_NUMBER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DATE_NOT_FOUND("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    // 주문 메뉴
    MENU_NOT_FOUND("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_COUNT_NOT_NUMBER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_COUNT_TOO_LOW("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_FORMAT_INVALID("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    // 전체 주문
    ORDER_MENU_DUPLICATE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_ONLY_BEVERAGE_NOT_ALLOWED("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_COUNT_EXCEEDS_LIMIT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return christmas.util.ExceptionHandler.exception(message);
    }

    public String getMessage() {
        return message;
    }
}