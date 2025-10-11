package racingcar.view;

import racingcar.model.in.SampleInput;
import camp.nextstep.edu.missionutils.Console;

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
}
