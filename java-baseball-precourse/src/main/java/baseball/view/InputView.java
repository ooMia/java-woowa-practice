package baseball.view;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputGuess'");
    }

    public char[] exitCodeInstruction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exitCodeInstruction'");
    }

    public int inputExitCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputExitCode'");
    }
}
