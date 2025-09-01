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
        // 1. 주어진 문자열의 길이가 3인지 (String.length)
        if (guess.length() != Constant.NUMBER_LENGTH) {
            throw new IllegalArgumentException();
        }

        int nBall = 0, nStrike = 0;
        boolean[] seen = new boolean[Constant.MAX_NUMBER + 1];
        // 2. 주어진 각 문자가 0이 아닌 [1, 9] 사이의 숫자인지 (Character.isDigit)
        // 3. 이전에 등장한 숫자인지 (boolean[])
        for (int i = 0; i < Constant.NUMBER_LENGTH; ++i) {
            char c = guess.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException();
            }

            int digit = c - '0';
            if (digit < Constant.MIN_NUMBER || Constant.MAX_NUMBER < digit) {
                throw new IllegalArgumentException();
            }
            if (seen[digit]) {
                throw new IllegalArgumentException();
            }
            seen[digit] = true;

            // 4. 스트라이크인지
            if (digit == answer[i]) {
                nStrike++;
                continue;
            }
            // 5. 볼인지
            for (int j = 0; j < Constant.NUMBER_LENGTH; ++j) {
                if (digit == answer[j]) {
                    nBall++;
                    break;
                }
            }
        }

        return new Result(nBall, nStrike);
    }

    private int[] generateAnswer() {
        int[] answer = new int[Constant.NUMBER_LENGTH];
        for (int i = 0; i < Constant.NUMBER_LENGTH; ++i) {
            answer[i] = Randoms.pickNumberInRange(Constant.MIN_NUMBER, Constant.MAX_NUMBER);
        }
        return answer;
    }

    public record Result(int ball, int strike) {
        @Override
        public String toString() {
            if (ball == 0 && strike == 0) {
                return Constant.WORD_NOTHING;
            }
            StringBuilder sb = new StringBuilder();
            if (ball > 0) {
                sb.append(ball).append(Constant.WORD_BALL);
            }
            if (ball > 0 && strike > 0) {
                sb.append(' ');
            }
            if (strike > 0) {
                sb.append(strike).append(Constant.WORD_STRIKE);
            }
            return sb.toString();
        }
    }
}
