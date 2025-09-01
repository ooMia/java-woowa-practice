package baseball;

import camp.nextstep.edu.missionutils.Randoms;

public class Computer {
    // 1. 랜덤 숫자 생성
    // 2. 추측 결과 반환

    int[] answer;

    public Computer() {
        this.answer = generateAnswer();
    }

    public void restart() {
        this.answer = generateAnswer();
    }

    private int[] generateAnswer() {
        int[] answer = new int[3];
        for (int i = 0; i < answer.length; ++i) {
            answer[i] = Randoms.pickNumberInRange(0, 9);
        }
        return answer;
    }

    public Result judge(String guess) {
        // 1. 주어진 문자열의 길이가 3인지 (String.length)
        if (guess.length() != 3) {
            throw new IllegalArgumentException();
        }

        int nBall = 0, nStrike = 0;
        boolean[] seen = new boolean[10];
        // 2. 주어진 각 문자가 0이 아닌 [1, 9] 사이의 숫자인지 (Character.isDigit)
        // 3. 이전에 등장한 숫자인지 (boolean[])
        for (int i = 0; i < 3; ++i) {
            char c = guess.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException();
            }

            int digit = c - '0';
            if (digit < 1 || 9 < digit) {
                throw new IllegalArgumentException();
            }
            if (seen[digit]) {
                throw new IllegalArgumentException();
            }
            seen[digit] = true;

            // 4. 스트라이크인지
            if (digit == answer[i]) {
                nStrike++;
            }
            // 5. 볼인지
            else if (digit == answer[(i + 1) % 3] || digit == answer[(i + 2) % 3]) {
                nBall++;
            }
        }

        return new Result(nBall, nStrike);
    }

    public record Result(int ball, int strike) {
        @Override
        public String toString() {
            if (ball == 0 && strike == 0) {
                return "낫싱";
            }
            StringBuilder sb = new StringBuilder();
            if (ball > 0) {
                sb.append(ball).append("볼");
            }
            if (ball > 0 && strike > 0) {
                sb.append(" ");
            }
            if (strike > 0) {
                sb.append(strike).append("스트라이크");
            }
            return sb.toString();
        }
    }
}
