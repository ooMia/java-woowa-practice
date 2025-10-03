package christmas.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExceptionHandlerTest {

    @Test
    void exception_withNull_returnsPrefix() {
        var ex = ExceptionHandler.exception(null);
        assertTrue(ex.getMessage().startsWith("[ERROR]"));
    }

    @Test
    void exception_withEmptyStringMessage_returnsPrefix() {
        var ex = ExceptionHandler.exception("");
        assertTrue(ex.getMessage().startsWith("[ERROR]"));
    }

    @Test
    void exception_withSingleMessage_returnsErrorPrefixAndMessage() {
        var ex = ExceptionHandler.exception("잘못된 입력입니다.");
        assertEquals("[ERROR] 잘못된 입력입니다.", ex.getMessage());
    }

    @Test
    void exception_withMultipleMessages_joinsMessagesWithSpaces() {
        var ex = ExceptionHandler.exception("입력값이 유효하지 않습니다.");
        assertEquals("[ERROR] 입력값이 유효하지 않습니다.", ex.getMessage());
    }

}