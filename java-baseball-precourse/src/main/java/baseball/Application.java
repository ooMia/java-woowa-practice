package baseball;

public class Application {
    static Controller api = new Controller();

    public static void main(String[] args) {
        // OUT_GREETING("숫자 야구 게임을 시작합니다."),
        api.printGreeting();

        var id = Constant.사용자_고유_번호;
        while (run(id) != Constant.기호_게임_재시작);
    }

    private static int run(int id) {
        api.reset(id);

        while (true) {
            // IN_GUESS_INSTRUCTION("숫자를 입력해주세요 : "),
            var guess = api.inputGuess();
            // 반복
            var guessResult = api.judge(guess);
            api.printGuessResult(guessResult);

            // OUT_BALL("볼"),
            // OUT_STRIKE("스트라이크"),
            // OUT_NOTHING("낫싱"),

            if (api.isCorrect(guessResult)) break;
        }

        // OUT_ANSWER_FOUND("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
        // OUT_NEW_GAME_QUESTION("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
        return api.inputExitCode();
        // 1: 다시 반복
        // 2: 반복 빠져나오기
    }
}
