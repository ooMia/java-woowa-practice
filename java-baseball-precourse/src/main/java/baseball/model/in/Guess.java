package baseball.model.in;

import java.util.HashSet;

import baseball.Constant;
import baseball.ErrorCode;

public record Guess(int[] ns) {
    public Guess {
        if (ns.length != Constant.자리_수_제한)
            throw ErrorCode.INPUT_문자열_길이.exception();

        var es = new HashSet<Integer>();
        for (var n : ns) es.add(n);

        if (es.size() != Constant.자리_수_제한)
            throw ErrorCode.INPUT_중복이_존재하는_숫자들.exception();

        for (var e : es)
            if (isNotInRange(e))
                throw ErrorCode.INPUT_1부터_9사이_아닌_문자.exception();
    }

    private static boolean isNotInRange(int n) {
        return n < Constant.각_자리_최솟값 || Constant.각_자리_최댓값 < n;
    }
}
