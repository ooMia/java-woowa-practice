package racingcar;

public class Game {

    private final racingcar.Car[] cars;

    public Game(racingcar.Car[] cars) {
        this.cars = cars;
    }

    public void playRound() {
        for (racingcar.Car car : cars) {
            car.move(camp.nextstep.edu.missionutils.Randoms.pickNumberInRange(0, 9));
        }
    }

    public Car[] getCars() {
        return cars;
    }

    public Car[] getWinners() {
        var winners = new java.util.ArrayList<Car>();
        winners.add(cars[0]);

        for (int i = 1; i < cars.length; ++i) {
            var car = cars[i];
            int standard = winners.get(0).getPosition();
            int challenger = car.getPosition();

            if (challenger < standard) continue;
            if (challenger > standard) winners.clear();
            winners.add(car);
        }
        return winners.toArray(new Car[0]);
    }
}
