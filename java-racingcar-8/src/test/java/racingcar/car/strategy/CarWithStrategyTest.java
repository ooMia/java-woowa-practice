package racingcar.car.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.IntSupplier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.car.CarFactory;

class CarWithStrategyTest {

    private IntSupplier getSupplier(int... numbers) {
        return new IntSupplier() {
            int i = 0;

            @Override
            public int getAsInt() {
                return numbers[i++];
            }
        };
    }

    @Test
    void move_withDefaultStrategy() {
        var supplier = getSupplier(4, 6, 1, 3);
        var car = CarFactory.ofRacingCarWithDefaultStrategy("test", supplier);

        car.move();
        car.move();
        assertEquals(2, car.getStatus().position());

        car.move();
        car.move();
        assertEquals(2, car.getStatus().position());
    }

    @Nested
    class CustomStrategyTest {

        private static int strategy(int value) {
            return value % 2;
        }

        @Test
        @SuppressWarnings("deprecation")
        void move() {
            var supplier = getSupplier(4, 6, 1, 3);
            var car = CarFactory.ofRacingCarWithStrategy("test", supplier);

            car.move(CustomStrategyTest::strategy);
            car.move(CustomStrategyTest::strategy);
            assertEquals(0, car.getStatus().position());

            car.move(CustomStrategyTest::strategy);
            car.move(CustomStrategyTest::strategy);
            assertEquals(2, car.getStatus().position());
        }
    }
}
