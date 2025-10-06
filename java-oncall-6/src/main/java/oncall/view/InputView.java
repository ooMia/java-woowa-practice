package oncall.view;

import java.util.stream.Stream;

import oncall.Constant;
import oncall.model.Employee;
import oncall.model.in.WorkDate;
import oncall.model.in.WorkOrder;
import oncall.util.Console;

public class InputView {
    // 1. WorkDate 비상 근무를 배정할 월과 시작 요일 입력 #L106
    public static WorkDate 일자_입력() {
        Console.print(Message.비상_근무_헤더.toString());
        var line = Console.readLine();
        var args = line.split("\\s*,\\s*");
        var month = Integer.parseInt(args[0]);
        var weekDay = Constant.문자열_요일_맵.get(args[1]);
        return new WorkDate(month, weekDay);
    }

    // 2-1. WorkOrder 평일 순번 입력 #L112
    public static WorkOrder 평일_순번_입력() {
        Console.print(Message.평일_순번_헤더.toString());
        var line = Console.readLine();
        var args = line.split("\\s*,\\s*");
        var list = Stream.of(args).map(Employee::new).toList();
        return new WorkOrder(list);
    }

    // 2. WorkOrder 휴일 순번 입력 #L112
    public static WorkOrder 휴일_순번_입력() {
        Console.print(Message.휴일_순번_헤더.toString());
        var line = Console.readLine();
        var args = line.split("\\s*,\\s*");
        var list = Stream.of(args).map(Employee::new).toList();
        return new WorkOrder(list);
    }
}
