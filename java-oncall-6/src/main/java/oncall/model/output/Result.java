package oncall.model.output;

import java.time.DayOfWeek;
import java.util.List;

import oncall.Constant;
import oncall.ErrorCode;
import oncall.model.Employee;

public record Result(int month, DayOfWeek start, List<Employee> orders) {
    public Result(int month, DayOfWeek start, List<Employee> orders) {
        this.month = month;
        this.start = start;
        this.orders = orders;

        if (month < Constant.MIN_MONTH || month > Constant.MAX_MONTH)
            throw ErrorCode.인자_오류.assertion();
        if (start == null)
            throw ErrorCode.인자_오류.assertion();
        // if (orders.size() < Constant.월별_말일.get(month))
        //     throw ErrorCode.인자_오류.assertion();
    }
}
