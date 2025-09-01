package baseball;

import baseball.Game.Status;

public class Application {
    public static void main(String[] args) {
        var game = new Game();
        while (game.run() != Status.GAME_OVER)
            ;
    }
}
