package racingcar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CarTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void testForward_전진() {
        var car = new Car("TEST");
        car.forward(MOVING_FORWARD);
        assertEquals(1, car.getPosition());
    }

    @Test
    void testForward_정지() {
        var car = new Car("TEST");
        car.forward(STOP);
        assertEquals(0, car.getPosition());
    }

    @Test
    void testForward_예외() {
        var car = new Car("TEST");
        assertThrows(RuntimeException.class, () -> car.forward(-1));
        assertThrows(RuntimeException.class, () -> car.forward(10));
    }
}
