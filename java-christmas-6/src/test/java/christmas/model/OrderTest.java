
package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private String getExpectedErrorMessage(ErrorCode errorCode) {
        return "[ERROR] " + errorCode.getMessage();
    }

    @Test
    @DisplayName("Order is created with valid menu strings")
    void testOrderCreationSuccess() {
        String[] menuStrings = {"양송이수프-2", "타파스-1"};
        Order order = new Order(menuStrings);

        Map<Menu, Integer> menus = order.getMenus();
        assertEquals(2, menus.size());
        assertEquals(2, menus.get(Menu.양송이수프));
        assertEquals(1, menus.get(Menu.타파스));
        assertEquals(6_000 * 2 + 5_500 * 1, order.getTotalPrice());
    }

    @Test
    @DisplayName("Throws exception for invalid menu format")
    void testInvalidMenuFormat() {
        String[] menuStrings = {"이상한주문2"};
        Exception exception = assertThrowsExactly(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.MENU_FORMAT_INVALID), exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for menu not found")
    void testMenuNotFound() {
        String[] menuStrings = {"없는메뉴-1"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.MENU_NOT_FOUND), exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for menu count not a number")
    void testMenuCountNotNumber() {
        String[] menuStrings = {"양송이수프-abc"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.MENU_COUNT_NOT_NUMBER), exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for menu count too low")
    void testMenuCountTooLow() {
        String[] menuStrings = {"양송이수프-0"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.MENU_COUNT_TOO_LOW), exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for duplicate menu")
    void testDuplicateMenu() {
        String[] menuStrings = {"양송이수프-1", "양송이수프-2"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.ORDER_MENU_DUPLICATE), exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for exceeding maximum order count")
    void testExceedingMaxOrderCount() {
        String[] menuStrings = {"양송이수프-21"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.ORDER_COUNT_EXCEEDS_LIMIT), exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for ordering only drinks")
    void testOrderingOnlyDrinks() {
        String[] menuStrings = {"제로콜라-1", "레드와인-1"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(menuStrings));
        assertEquals(getExpectedErrorMessage(ErrorCode.ORDER_ONLY_BEVERAGE_NOT_ALLOWED), exception.getMessage());
    }
}