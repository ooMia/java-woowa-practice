package vendingmachine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import vendingmachine.model.TradableItem;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void testToItem() {

    }

    @Test
    void testToUserBalance() {

    }

    @Test
    void testToVendorBalance() {

    }

    @Test
    void testToVendorItems() {
        var input = "[콜라,1500,20];[사이다,1000,10]";
        var actual = parser.toVendorItems(input).map();

        assertEquals(actual.get(new TradableItem("콜라", 1500)), 20);
        assertEquals(actual.get(new TradableItem("사이다", 1000)), 10);
    }
}
