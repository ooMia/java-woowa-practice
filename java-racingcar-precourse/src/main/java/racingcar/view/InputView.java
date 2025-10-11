package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.model.in.SampleInput;

public class InputView {
    public void inputBooHeader() {
        System.out.print(Message.IN_INSTRUCTION.text());
    }

    public SampleInput readBoo() {
        var number = Integer.parseInt(Console.readLine());
        return new SampleInput(number);
    }

    public String readLine() {
        return Console.readLine();
    }

    public char[] CarNamesInstruction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CarNamesInstruction'");
    }

    public <T> T inputCarNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputCarNames'");
    }
}
