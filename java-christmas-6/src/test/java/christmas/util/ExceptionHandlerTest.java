package christmas.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExceptionHandlerTest {

    @Test
    void exception_withNoMessages_returnsDefaultErrorMessage() {
        IllegalArgumentException ex = ExceptionHandler.exception();
        assertEquals("[ERROR] 처리되지 않은 오류가 발생했습니다.", ex.getMessage());
    }

    @Test
    void exception_withSingleMessage_returnsErrorPrefixAndMessage() {
        IllegalArgumentException ex = ExceptionHandler.exception("잘못된 입력입니다.");
        assertEquals("[ERROR] 잘못된 입력입니다.", ex.getMessage());
    }

    @Test
    void exception_withMultipleMessages_joinsMessagesWithSpaces() {
        IllegalArgumentException ex = ExceptionHandler.exception("입력값이", "유효하지", "않습니다.");
        assertEquals("[ERROR] 입력값이 유효하지 않습니다.", ex.getMessage());
    }

    @Test
    void exception_withEmptyStringMessage_includesEmptySpace() {
        IllegalArgumentException ex = ExceptionHandler.exception("");
        assertEquals("[ERROR] ", ex.getMessage());
    }
}