package baseball.service;

import java.util.List;

import baseball.Constant;
import baseball.model.in.Guess;
import baseball.model.out.GuessResult;

public class MainService {
    private final AnswerStorageService service = new AnswerStorageService();

    public boolean isAnswer(GuessResult result) {
        return result.nStrikes() == Constant.자리_수_제한;
    }

    // id 매핑된 정답 객체가 없다면 생성하고, 있다면 다시 생성
    public void resetAnswer(int id) {
        service.recreate(id);
    }

    public GuessResult judge(int id, Guess guess) {
        List<Integer> answer = service.get(id);
        return judge(answer, guess.ns());
    }

    private GuessResult judge(List<Integer> answer, int[] guess) {
        int nBalls = 0, nStrikes = 0;
        for (int i = 0; i < guess.length; ++i) {
            var n = guess[i];
            if (answer.get(i) == n) {
                ++nStrikes;
                continue;
            }
            if (answer.contains(n))
                ++nBalls;
        }
        return new GuessResult(nBalls, nStrikes);
    }
}
