package christmas;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import christmas.model.Menu;

public class EdgeTest {
    @Test
    void 자료형_상한_테스트() {
        Menu mostExpensiveMenu = Stream.of(Menu.values())
                .max(Comparator.comparingInt(Menu::getPrice))
                .orElse(null);
        int maxQuantity = Constant.MAX_TOTAL_ORDER_QUANTITY;
        long totalPrice = (long) mostExpensiveMenu.getPrice() * maxQuantity;

        if (totalPrice > Integer.MAX_VALUE) {
            fail("전체 주문 금액이 Integer 범위를 초과할 수 있습니다.");
        }
    }
}
