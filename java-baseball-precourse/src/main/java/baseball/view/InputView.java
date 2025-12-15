package baseball.view;

import baseball.Constant;
import baseball.ErrorCode;
import baseball.model.in.ExitCode;
import baseball.model.in.Guess;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String guessInstruction() {
        return Message.IN_GUESS_INSTRUCTION.text();
    }

    public String readLine() {
        return Console.readLine();
    }

    public Guess inputGuess() {
        int[] ns = readInts(); // 기본 예외 테스트를 참조하면, 모든 입력이 명시적으로 변환되어야 한다
        return new Guess(ns);
    }

    public String exitCodeInstruction() {
        return Message.IN_NEW_GAME_QUESTION.format(Constant.기호_게임_재시작, Constant.기호_게임_종료);
    }

    public ExitCode inputExitCode() {
        var number = Integer.parseInt(Console.readLine());
        return new ExitCode(number);
    }

    private int[] readInts() {
        try {
            var line = Console.readLine();
            int[] res = new int[line.length()];
            for (int i = 0; i < res.length; ++i)
                res[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            return res;
        } catch (Exception e) {
            throw ErrorCode.INPUT_INVALID.exception();
        }
    }
}
