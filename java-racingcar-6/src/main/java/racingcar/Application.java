package racingcar;

public class Application {
    public static void main(String[] args) {
        var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        var view = new racingcar.View(bw);
        var api = new racingcar.Controller(view);

        var cars = api.inputCars();
        var game = api.createGame(cars);

        var nRounds = api.inputNumberOfTrials();
        api.playRounds(game, nRounds);
    }
}
