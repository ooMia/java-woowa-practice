package baseball.service;

import baseball.model.in.Guess;
import baseball.model.in.SampleInput;
import baseball.model.out.GuessResult;
import baseball.model.out.SampleOutput;

public class MainService {
    private final SampleSubService service = new SampleSubService();

    public SampleOutput process(SampleInput input) {
        var message = service.doWork("ok");
        return new SampleOutput(input.a(), message);
    }

    public GuessResult judge(Guess guess) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'judge'");
    }

    public boolean isAnswer(Object guessResult) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAnswer'");
    }

    public void resetAnswer(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetAnswer'");
    }
}
