package baseball;

public class Application {
    static Controller api = new Controller();

    public static void main(String[] args) {
        api.printGreeting();
        
        var id = Constant.사용자_고유_번호;
        while (run(id) != Constant.기호_게임_종료);
    }

    private static int run(int id) {
        api.reset(id);

        while (true) {
            var guess = api.inputGuess();
            var guessResult = api.judge(id, guess);
            api.printGuessResult(guessResult);

            if (api.isCorrect(guessResult)) break;
        }
        return api.inputExitCode();
    }
}
