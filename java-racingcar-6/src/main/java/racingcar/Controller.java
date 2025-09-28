package racingcar;

public class Controller {

    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    public String[] inputCarNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputCarNames'");
    }

    public int inputNumberOfTrials() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputNumberOfTrials'");
    }

    public racingcar.Game createGame(String[] names) {
        return new racingcar.Game(names);
    }

    public void playRound(racingcar.Game game) {
        game.playRound();
    }
}
