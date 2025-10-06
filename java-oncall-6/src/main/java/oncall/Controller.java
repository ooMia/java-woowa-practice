package oncall;

import oncall.model.in.WorkDate;
import oncall.model.in.WorkOrder;
import oncall.model.output.Result;
import oncall.service.MainService;
import oncall.util.ExceptionHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {

    private static MainService service = new MainService(null, null, null);

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
        var orders = new MainService(o1, o2, o3).로직();
        return new Result(o1.month(), o1.start(), orders);
    }

    public static void 결과_출력(Result result) {
        OutputView.printResult(result);
    }
}
