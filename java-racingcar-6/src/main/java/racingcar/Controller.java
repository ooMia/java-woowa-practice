package racingcar;

public class Controller {
    private static final String INPUT_CARS_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_CARS_DELIMITER = ",";
    private static final String INPUT_TRIALS_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String PLAY_RESULT_MESSAGE = "실행 결과";

    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    public racingcar.Car[] inputCars() {
        view.println(INPUT_CARS_MESSAGE);
        return throwIfFailed(() -> {
            var line = camp.nextstep.edu.missionutils.Console.readLine();
            var names = line.split(INPUT_CARS_DELIMITER);

            var cars = new racingcar.Car[names.length];
            for (int i = 0; i < names.length; ++i) {
                cars[i] = new racingcar.Car(names[i]);
            }
            return cars;
        });
    }

    public int inputNumberOfTrials() {
        view.println(INPUT_TRIALS_MESSAGE);
        return throwIfFailed(() -> {
            var line = camp.nextstep.edu.missionutils.Console.readLine();
            return Integer.parseInt(line);
        });
    }

    public racingcar.Game createGame(racingcar.Car[] cars) {
        return new racingcar.Game(cars);
    }

    public void playRounds(Game game, int nRounds) {
        view.println('\n' + PLAY_RESULT_MESSAGE);
        for (int i = 0; i < nRounds; ++i) {
            game.playRound();
            view.printStatus(game.getCars());
        }
        view.printWinners(game.getWinners());
    }

    private static <T> T throwIfFailed(java.util.function.Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
