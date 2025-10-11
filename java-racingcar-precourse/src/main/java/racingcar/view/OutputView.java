package racingcar.view;

import racingcar.model.out.RacingStatus;
import racingcar.model.out.RacingWinners;
import racingcar.model.out.SampleOutput;

public class OutputView {

    public void print(SampleOutput output) {
        System.out.println(Message.OUT_QUESTION_FORMAT.format(output.foo()));
        System.out.println(output.boo());
    }

    public char[] racingHeader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'racingHeader'");
    }

    public char[] status(RacingStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'status'");
    }

    public char[] winners(RacingWinners winners) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'winners'");
    }
}
