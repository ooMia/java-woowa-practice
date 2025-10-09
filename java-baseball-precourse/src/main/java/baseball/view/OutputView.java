package baseball.view;

import baseball.model.out.SampleOutput;

public class OutputView {

    public void print(SampleOutput output) {
        System.out.println(Message.OUT_QUESTION_FORMAT.format(output.foo()));
        System.out.println(output.boo());
    }
}
