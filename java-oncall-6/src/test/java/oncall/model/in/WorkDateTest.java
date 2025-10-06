package oncall.model.in;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WorkDateTest {

    @Test
    void testOf() {
        var o = WorkDate.of("5 , 월");
        assertEquals(5, o.month());
        assertEquals("월", o.weekDay());
    }
}
