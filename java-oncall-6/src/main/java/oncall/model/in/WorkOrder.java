package oncall.model.in;

import java.util.List;
import java.util.stream.Stream;

import oncall.model.Employee;

record WorkOrder(List<Employee> orders) {
    WorkOrder(List<Employee> orders) {
        this.orders = orders;

        // TODO: validation
    }

    public static WorkOrder of(String line) {
        var args = line.split("\\s*,\\s*");
        var list = Stream.of(args).map(Employee::new).toList();
        return new WorkOrder(list);
    }
}
