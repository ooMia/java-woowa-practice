package baseball.view;

import baseball.Constant;
import baseball.model.in.ExitCode;
import baseball.model.in.Guess;
import baseball.model.in.SampleInput;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String guessInstruction() {
        return Message.IN_GUESS_INSTRUCTION.text();
    }

    public SampleInput readBoo() {
        var number = Integer.parseInt(Console.readLine());
        return new SampleInput(number);
    }

    public String readLine() {
        return Console.readLine();
    }

    public Guess inputGuess() {
        var line = Console.readLine();
        int[] ns = new int[Constant.자리_수_제한];
        for (int i = 0; i < ns.length; ++i)
            ns[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
        return new Guess(ns);
    }

    public String exitCodeInstruction() {
        return Message.IN_NEW_GAME_QUESTION.format(Constant.기호_게임_재시작, Constant.기호_게임_종료);
    }

    public ExitCode inputExitCode() {
        var number = Integer.parseInt(Console.readLine());
        return new ExitCode(number);
    }
}
