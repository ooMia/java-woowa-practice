package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RacingCarTest {

    private static RacingCar generator(String name) {
        // bypass factory name validation
        return new RacingCarImpl(name);
    }

    @Test
    void status_sorted() {
        var cars = java.util.List.of(generator("d1"), generator("d0"));

        cars.getFirst().move(1);
        var sortedSnapshot = cars.stream().map(RacingCar::getStatus).sorted().toList();

        assertEquals(1, sortedSnapshot.getLast().position());
    }

    @Nested
    class CarTest {
        private Car car;

        @BeforeEach
        void setUp() {
            car = generator("test");
        }

        @Test
        void move() {
            car.move(1);
            assertEquals(1, car.getStatus().position());

            car.move(-1);
            assertEquals(0, car.getStatus().position());
        }

        @Test
        void moveBackward_resultNonNegativePosition() {
            car.move(-1);
            assertEquals(0, car.getStatus().position());
        }

        @Test
        void moveForward_exceedLimitThrows() {
            car.move(Integer.MAX_VALUE);
            assertThrows(IllegalStateException.class, () -> car.move(1));
        }
    }
}
