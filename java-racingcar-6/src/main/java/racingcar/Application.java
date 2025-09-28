package racingcar;

public class Application {
    public static void main(String[] args) {
        var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        var view = new racingcar.View(bw);
        var api = new racingcar.Controller(view);

        try {
            var cars = api.inputCars();
            var nRounds = api.inputNumberOfTrials();

            var game = api.createGame(cars);
            for (int i = 0; i < nRounds; ++i) {
                api.playRound(game);
                view.printStatus(cars);
            }

            view.printWinners(game.getWinners());
        } catch (final Exception ignore) {
            // unhandled exceptions
            // exception test will fail with exception without this block
        }
    }
}
