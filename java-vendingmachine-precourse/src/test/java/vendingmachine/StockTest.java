package vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vendingmachine.vault.Stock;

class StockTest {

    @Test
    void of_case1() {
        String input = "[콜라,1500,20]";
        var expected = new Stock("콜라", 1500, 20);
        Assertions.assertEquals(expected, Stock.of(input));
    }

    @Test
    void of_case2() {
        String input = "[사이다,1000,10]";
        var expected = new Stock("사이다", 1000, 10);
        Assertions.assertEquals(expected, Stock.of(input));
    }
}
