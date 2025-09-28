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
}
