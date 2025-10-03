package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class ConstantTest {

    @Test
    public void testEventPeriodConstants() {
        assertEquals(2023, Constant.EVENT_YEAR);
        assertEquals(12, Constant.EVENT_MONTH);
        assertEquals(1, Constant.EVENT_START_DAY);
        assertEquals(31, Constant.EVENT_END_DAY); // December 2023 has 31 days
    }

    @Test
    public void testWeekends() {
        assertTrue(Constant.WEEKENDS.contains(DayOfWeek.FRIDAY));
        assertTrue(Constant.WEEKENDS.contains(DayOfWeek.SATURDAY));
    }

    @Test
    public void testWeekdays() {
        assertTrue(Constant.WEEKDAYS.contains(DayOfWeek.SUNDAY));
        assertTrue(Constant.WEEKDAYS.contains(DayOfWeek.MONDAY));
        assertTrue(Constant.WEEKDAYS.contains(DayOfWeek.TUESDAY));
        assertTrue(Constant.WEEKDAYS.contains(DayOfWeek.WEDNESDAY));
        assertTrue(Constant.WEEKDAYS.contains(DayOfWeek.THURSDAY));
    }

}