package baseball;

import camp.nextstep.edu.missionutils.Randoms;

public class Computer {
    // 1. 랜덤 숫자 생성
    // 2. 추측 결과 반환

    private int[] answer = generateAnswer();

    public void reset() {
        this.answer = generateAnswer();
    }

    public Result judge(String guess) {
        // 1. 문자열 길이 검사
        if (guess.length() != Constant.NUMBER_LENGTH) {
            throw new IllegalArgumentException();
        }

        int nBall = 0, nStrike = 0;
        boolean[] seen = new boolean[Constant.MAX_NUMBER + 1];
        for (int i = 0; i < Constant.NUMBER_LENGTH; ++i) {
            int digit = charToInt(guess.charAt(i));

            // 2. 이전에 등장한 숫자인지 (boolean[])
            if (seen[digit])
                throw new IllegalArgumentException();
            seen[digit] = true;

            // 3. 스트라이크/볼 판별
            if (digit == answer[i])
                ++nStrike;
            else if (contains(answer, digit))
                ++nBall;
        }
        return new Result(nBall, nStrike);
    }

    private int[] generateAnswer() {
        int[] answer = new int[Constant.NUMBER_LENGTH];
        boolean[] seen = new boolean[Constant.MAX_NUMBER + 1];
        for (int i = 0, num; i < Constant.NUMBER_LENGTH; ++i) {
            do {
                num = Randoms.pickNumberInRange(Constant.MIN_NUMBER, Constant.MAX_NUMBER);
            } while (seen[num]);
            seen[num] = true;
            answer[i] = num;
        }
        return answer;
    }

    private int charToInt(char c) {
        // 주어진 문자가 유효한 범위 내 숫자인 경우에만 변환
        int digit = c - '0';
        if (digit < Constant.MIN_NUMBER || Constant.MAX_NUMBER < digit)
            throw new IllegalArgumentException();
        return digit;
    }

    private boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target)
                return true;
        }
        return false;
    }

    public record Result(int ball, int strike) {
        @Override
        public String toString() {
            if (ball == 0 && strike == 0)
                return Constant.WORD_NOTHING;

            StringBuilder sb = new StringBuilder();
            if (ball > 0)
                sb.append(ball).append(Constant.WORD_BALL);
            if (ball > 0 && strike > 0)
                sb.append(' ');
            if (strike > 0)
                sb.append(strike).append(Constant.WORD_STRIKE);
            return sb.toString();
        }
    }
}
