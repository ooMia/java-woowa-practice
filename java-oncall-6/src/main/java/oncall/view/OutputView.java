package oncall.view;

import oncall.Constant;
import oncall.model.output.Result;
import oncall.util.Console;

public class OutputView {

    // 3. 출력 #L124
    public static void printResult(Result r) {
        int day = 1;
        for (var employee : r.orders())
            Console.println(String.format(
                    "%d월 %일 %s %s",
                    r.month(),
                    day++,
                    Constant.요일_문자열_맵.get(r.start()),
                    employee.name()
            ));
    }
}
