package oncall.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;

import oncall.model.Employee;
import oncall.model.output.Result;
import oncall.util.Console;

public class OutputViewTest {
    @Test
    void testToString() {
        var employees = List.of("오션", "로이스", "허브", "쥬니", "말랑").stream().map(Employee::new).toList();
        var result = new Result(4, DayOfWeek.SATURDAY, employees);
        var expect = """
                4월 1일 토 오션
                4월 2일 일 로이스
                4월 3일 월 허브
                4월 4일 화 쥬니
                4월 5일 수 말랑""";

        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var console = new Console(bw);
        var ov = new OutputView(console);
        assertEquals(expect, ov.toString(result));
    }
}
