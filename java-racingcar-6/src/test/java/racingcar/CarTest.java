package racingcar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CarTest {
    @Test
    void carNameShouldNotExceedMaxLength() {
        assertThrows(IllegalArgumentException.class, () -> new Car("abcdef"));
    }

    @Test
    void carNameWithinMaxLengthShouldBeAccepted() {
        assertDoesNotThrow(() -> new Car("abcde"));
    }

    @Test
    void carInitialPositionShouldBeZero() {
        Car car = new Car("car");
        assertEquals(0, car.getPosition());
    }

    @Test
    void carShouldMoveWhenFuelIsEnough() {
        Car car = new Car("car");
        car.move(4);
        assertEquals(1, car.getPosition());
    }

    @Test
    void carShouldNotMoveWhenFuelIsNotEnough() {
        Car car = new Car("car");
        car.move(3);
        assertEquals(0, car.getPosition());
    }

    @Test
    void getNameShouldReturnCarName() {
        Car car = new Car("test");
        assertEquals("test", car.getName());
    }
}