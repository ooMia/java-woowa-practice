package oncall.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testIsHoliday() {
        assertTrue(new Date(1, 1).isHoliday());
        assertTrue(new Date(5, 5).isHoliday());
    }

    @Test
    void testIsWeekEnd_1() {
        var start = DayOfWeek.MONDAY;
        var sunday = new Date(1, 1 + 6);
        assertEquals(sunday.toDayOfWeek(start), DayOfWeek.SUNDAY);
        assertTrue(sunday.isWeekEnd(start));
    }

    @Test
    void testIsWeekEnd_2() {
        var start = DayOfWeek.SATURDAY;
        var holiday = new Date(5, 5);
        assertTrue(holiday.isHoliday());
        assertEquals(holiday.toDayOfWeek(start), DayOfWeek.WEDNESDAY);
        assertTrue(!holiday.isWeekEnd(start));
    }
}
