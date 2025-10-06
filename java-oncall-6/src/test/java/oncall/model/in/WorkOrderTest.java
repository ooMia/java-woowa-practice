package oncall.model.in;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import oncall.model.Employee;

public class WorkOrderTest {
    @Test
    void testOf() {
        var o = WorkOrder.of("준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리");
        assertEquals(new Employee("준팍"), o.orders().get(0));
        assertEquals(new Employee("도리"), o.orders().get(o.orders().size() - 1));
    }
}
