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

    // TODO 입력이 실패해야 하는 상황이 model.in에 대한 단위 테스트로 모두 작성되었는가?
}
