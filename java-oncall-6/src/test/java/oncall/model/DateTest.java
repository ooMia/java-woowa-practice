package oncall.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    void testToDayOfWeek_1() {
        var start = DayOfWeek.MONDAY;
        var first = new Date(1, 1).toDayOfWeek(start);
        assertEquals(first, DayOfWeek.MONDAY);

        var second = new Date(1, 2).toDayOfWeek(start);
        assertEquals(second, DayOfWeek.TUESDAY);
    }

    @Test
    void testToDayOfWeek_2() {
        var start = DayOfWeek.MONDAY;
        var first = new Date(1, 1).toDayOfWeek(start);
        assertEquals(first, DayOfWeek.MONDAY);

        var second = new Date(1, 1 + 7).toDayOfWeek(start);
        assertEquals(second, DayOfWeek.MONDAY);
    }
}
