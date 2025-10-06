package oncall.view;

import oncall.Constant;
import oncall.model.output.Result;
import oncall.util.Console;

public class OutputView {

    // 3. 출력 #L124
    public static void printResult(Result r) {
        Console.print("");
        Console.print(toString(r));
    }

    static String toString(Result r) {
        var sb = new StringBuilder();
        int day = 1;
        var dayOfWeek = r.start();
        for (var employee : r.orders()) {
            sb.append(String.format(
                    "%d월 %d일 %s %s%s",
                            r.month(),
                    day++,
                    Constant.요일_문자열_맵.get(dayOfWeek),
                    employee.name(),
                    System.lineSeparator()
            ));
            dayOfWeek = dayOfWeek.plus(1);
        }
        return sb.toString().trim();
    }
}
