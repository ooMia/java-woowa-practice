package racingcar.periphery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.IntSupplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.car.Car.Status;
import racingcar.car.CarFactory;

class RaceGameTest {

    private RaceGame game;

    private IntSupplier getSupplier(int... numbers) {
        return new IntSupplier() {
            int i = 0;

            @Override
            public int getAsInt() {
                return numbers[i++];
            }
        };
    }

    @BeforeEach
    void setUp() {
        var names = List.of("a", "b", "c");
        IntSupplier randomSupplier = getSupplier(0, 2, 4, 6, 3, 0);
        var cars = names.stream().map(name -> CarFactory.ofRacingCarWithDefaultStrategy(name, randomSupplier)).toList();
        game = new RaceGame(cars);
    }

    @Test
    void iterate() {
        {
            var positions = game.iterate().stream().map(Status::position).toList();
            assertEquals(List.of(0, 0, 1), positions);
        }
        {
            var positions = game.iterate().stream().map(Status::position).toList();
            assertEquals(List.of(1, 0, 1), positions);
        }
    }

    @Test
    void getWinners() {
        {
            game.iterate();
            var winners = game.getWinners().stream().map(Status::name).toList();
            assertEquals(List.of("c"), winners);
        }
        {
            game.iterate();
            var winners = game.getWinners().stream().map(Status::name).toList();
            assertEquals(List.of("a", "c"), winners);
        }
    }
}
