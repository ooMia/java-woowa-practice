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
        assertTrue(Constant.WEEKEND_DAYS.contains(DayOfWeek.FRIDAY));
        assertTrue(Constant.WEEKEND_DAYS.contains(DayOfWeek.SATURDAY));
    }

    @Test
    public void testChristmasIsWeekday() {
        var date = java.time.LocalDate.of(2023, 12, 25);
        var dayOfWeek = date.getDayOfWeek();
        assertTrue(!Constant.WEEKEND_DAYS.contains(dayOfWeek));
    }

}