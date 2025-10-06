package oncall.model.in;

import java.util.List;
import java.util.stream.Stream;

import oncall.Constant;
import oncall.ErrorCode;
import oncall.model.Employee;

public record WorkOrder(List<Employee> orders) {
    public WorkOrder(List<Employee> orders) {
        this.orders = orders;
        var length = orders.size();

        if (length < Constant.MIN_EMPLOYEE || length > Constant.MAX_EMPLOYEE)
            throw ErrorCode.유효하지_않은_입력.exception();
    }

    // helper method for testing
    static WorkOrder of(String line) {
        var args = line.split("\\s*,\\s*");
        var list = Stream.of(args).map(Employee::new).toList();
        return new WorkOrder(list);
    }
}
