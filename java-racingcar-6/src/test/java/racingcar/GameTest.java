package racingcar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class GameTest {

    static class StubCar extends Car {
        private int position;

        public StubCar(String name, int position) {
            super(name);
            this.position = position;
        }

        @Override
        public void move(int number) {
            position += number;
        }

        @Override
        public int getPosition() {
            return position;
        }
    }

    @Test
    void testGetWinnersSingleWinner() {
        StubCar car1 = new StubCar("car1", 2);
        StubCar car2 = new StubCar("car2", 1);
        StubCar car3 = new StubCar("car3", 0);
        Game game = new Game(new Car[]{car1, car2, car3});
        List<Car> winners = List.of(game.getWinners());
        assertEquals(1, winners.size());
        assertEquals(car1, winners.get(0));
    }

    @Test
    void testGetWinnersMultipleWinners() {
        StubCar car1 = new StubCar("car1", 3);
        StubCar car2 = new StubCar("car2", 3);
        StubCar car3 = new StubCar("car3", 1);
        Game game = new Game(new Car[]{car1, car2, car3});
        List<Car> winners = List.of(game.getWinners());
        assertEquals(2, winners.size());
        assertTrue(winners.contains(car1));
        assertTrue(winners.contains(car2));
    }
}