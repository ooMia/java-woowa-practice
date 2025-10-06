package oncall.view;

import oncall.Constant;
import oncall.model.Date;
import oncall.model.output.Result;
import oncall.util.Console;

public class OutputView {
    private final Console console;

    public OutputView(Console c) {
        this.console = c;
    }

    // 3. 출력 #L124
    public void printResult(Result r) {
        console.print(toString(r));
    }

    String toString(Result r) {
        var sb = new StringBuilder();
        int day = 1;
        final var startDayOfWeek = r.start();
        var dayOfWeek = startDayOfWeek;

        for (var employee : r.orders()) {
            var date = new Date(r.month(), day);
            var isHoliday = "";
            if (date.isHoliday() && !date.isWeekEnd(startDayOfWeek)) isHoliday = "(휴일)";
            sb.append(String.format(
                    "%d월 %d일 %s%s %s%s",
                    r.month(),
                    day++,
                    Constant.요일_문자열_맵.get(dayOfWeek),
                    isHoliday,
                    employee.name(),
                    System.lineSeparator()
            ));
            dayOfWeek = dayOfWeek.plus(1);
        }
        return sb.toString().trim();
    }
}
