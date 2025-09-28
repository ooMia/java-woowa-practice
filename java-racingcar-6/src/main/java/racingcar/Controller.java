package racingcar;

public class Controller {

    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    public racingcar.Car[] inputCars() {
        return throwIfFailed(() -> {
            var line = camp.nextstep.edu.missionutils.Console.readLine();
            var names = line.split(","); // TODO use Constant.DELIMITER

            var cars = new racingcar.Car[names.length];
            for (int i = 0; i < names.length; ++i) {
                cars[i] = new racingcar.Car(names[i]);
            }
            return cars;
        });
    }

    public int inputNumberOfTrials() {
        return throwIfFailed(() -> {
            var line = camp.nextstep.edu.missionutils.Console.readLine();
            return Integer.parseInt(line);
        });
    }

    public racingcar.Game createGame(racingcar.Car[] cars) {
        return new racingcar.Game(cars);
    }

    public void playRound(racingcar.Game game) {
        game.playRound();
    }

    private static <T> T throwIfFailed(java.util.function.Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
