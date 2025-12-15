package racingcar.periphery;

import java.util.function.IntSupplier;
import racingcar.Console;
import racingcar.car.CarFactory;
import racingcar.periphery.view.InputView;
import racingcar.periphery.view.OutputView;

public class GameScenario implements Runnable {

    private final InputView inputView;
    private final OutputView outputView;
    private final IntSupplier randomSupplier;

    public GameScenario(Console console, IntSupplier randomSupplier) {
        this.inputView = new InputView(console);
        this.outputView = new OutputView(console);
        this.randomSupplier = randomSupplier;
    }

    @Override
    public void run() {
        var names = inputView.readCarNames();
        var cars = names.stream().map(this::carGenerator).toList();
        var game = new RaceGame(cars);

        var iteration = inputView.readGameIteration();
        outputView.printCustomMessage(System.lineSeparator() + "실행 결과");
        while (iteration-- > 0) {
            var status = game.iterate();
            outputView.printStatus(status);
        }
        outputView.printWinners(game.getWinners());
    }

    private racingcar.car.strategy.RacingCarWithDefaultStrategy carGenerator(String name) {
        return CarFactory.ofRacingCarWithDefaultStrategy(name, randomSupplier);
    }
}
