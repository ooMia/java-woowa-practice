package oncall.model.in;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class WorkDateTest {

    @Test
    void testOf() {
        var o = WorkDate.of("5 , 월");
        assertEquals(5, o.month());
        assertEquals(DayOfWeek.MONDAY, o.weekDay());
    }
}
