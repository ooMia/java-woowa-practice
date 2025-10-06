package baseball;

public class Application {
    public static void main(String[] args) {
        var game = new Game();
        while (game.isRunning())
            game.run();
    }
}
