package baseball.service;

import java.util.List;

import baseball.Constant;
import baseball.model.in.Guess;
import baseball.model.in.SampleInput;
import baseball.model.out.GuessResult;
import baseball.model.out.SampleOutput;

public class MainService {
    private final AnswerStorageService service = new AnswerStorageService();

    public SampleOutput process(SampleInput input) {
        var message = service.doWork("ok");
        return new SampleOutput(input.a(), message);
    }

    public boolean isAnswer(GuessResult result) {
        return result.nStrikes() == Constant.자리_수_제한;
    }

    // id 매핑된 정답 객체가 없다면 생성하고, 있다면 다시 생성
    public void resetAnswer(int id) {
        service.recreate(id);
    }

    public GuessResult judge(int id, Guess guess) {
        List<Integer> obj = service.get(id);
        var ns = guess.ns();

        int nBalls = 0, nStrikes = 0;
        for (int i = 0; i < ns.length; ++i) {
            var n = ns[i];
            if (obj.get(i) == n) {
                ++nStrikes;
                continue;
            }
            if (obj.contains(n))
                ++nBalls;
        }
        return new GuessResult(nBalls, nStrikes);
    }
}
