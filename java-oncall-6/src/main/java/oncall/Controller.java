package oncall;

import java.time.DayOfWeek;
import java.util.List;

import oncall.model.Employee;
import oncall.model.in.WorkDate;
import oncall.model.in.WorkOrder;
import oncall.model.output.Result;
import oncall.util.ExceptionHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {
    public static WorkDate 일자_입력() {
        return ExceptionHandler.tryUntilValid(InputView::일자_입력);
    }

    public static WorkOrder[] 순번_입력() {
        return ExceptionHandler.tryUntilValid(() -> {
            return new WorkOrder[]{
                    InputView.평일_순번_입력(),
                    InputView.휴일_순번_입력()
            };
        });
    }

    public static Result 결과_생성(WorkDate o1, WorkOrder o2, WorkOrder o3) {
        // TODO implement
        var employees = List.of("오션", "로이스", "허브", "쥬니", "말랑").stream().map(Employee::new).toList();
        return new Result(4, DayOfWeek.SATURDAY, employees);
    }

    public static void 결과_출력(Result result) {
        OutputView.printResult(result);
    }
}
