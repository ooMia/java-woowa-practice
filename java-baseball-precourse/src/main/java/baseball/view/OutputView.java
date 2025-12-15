package baseball.view;

import baseball.Constant;
import baseball.model.out.GuessResult;

public class OutputView {

    public String greeting() {
        return Message.OUT_GREETING.text();
    }

    // 낫싱
    // 1볼
    // 1스트라이크
    // 1볼 1스트라이크
    public String getResult(GuessResult result) {
        if (result.hasNothing()) return Message.OUT_NOTHING.text();

        var sb = new StringBuilder();
        if (result.hasBall())
            sb.append(result.nBalls()).append(Message.OUT_BALL.text());
        if (result.hasStrike()) {
            if (result.hasBall()) sb.append(' ');
            sb.append(result.nStrikes()).append(Message.OUT_STRIKE.text());
        }
        return sb.toString();
    }

    public String celebrate() {
        return Message.OUT_ANSWER_FOUND_FORMAT.format(Constant.자리_수_제한);
    }
}
