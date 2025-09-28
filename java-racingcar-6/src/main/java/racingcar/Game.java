package racingcar;

public class Game {

    private final racingcar.Car[] cars;

    public Game(String[] names) {
        this.cars = new racingcar.Car[names.length];
        for (int i = 0; i < names.length; ++i) {
            this.cars[i] = new racingcar.Car(names[i]);
        }
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

        for (var car : cars) {
            int standard = winners.get(0).getPosition();
            int challenger = car.getPosition();

            if (challenger < standard) continue;
            if (challenger > standard) winners.clear();
            winners.add(car);
        }
        return winners.toArray(new Car[0]);
    }
}
