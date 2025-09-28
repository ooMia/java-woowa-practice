package racingcar;

public class Application {
    public static void main(String[] args) {
        var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        var view = new racingcar.View(bw);
        var api = new racingcar.Controller(view);

        try {
            var names = api.inputCarNames();
            var nRounds = api.inputNumberOfTrials();

            // var game = new racingcar.Game(names);
            var game = api.createGame(names);
            for (int i = 0; i < nRounds; ++i) {
                // game.moveCars();
                api.playRound(game);
                view.printStatus(game);
            }

            view.printWinners(game);
        } catch (final Exception ignore) {
            // unhandled exceptions
            // exception test will fail with exception without this block
        }
    }
}
