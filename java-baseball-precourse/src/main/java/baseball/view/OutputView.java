package baseball.view;

import baseball.model.out.GuessResult;
import baseball.model.out.SampleOutput;

public class OutputView {

    public void print(SampleOutput output) {
        System.out.println(Message.OUT_QUESTION_FORMAT.format(output.foo()));
        System.out.println(output.boo());
    }

    public char[] greeting() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'greeting'");
    }

    public char[] getResult(GuessResult result) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }
}
