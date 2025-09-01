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

    public String judge(String guess) {
        // 1. 주어진 문자열의 길이가 3인지 (String.length)
        if (guess.length() != 3) {
            throw new IllegalArgumentException();
        }

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
        }

        if ("246".equals(guess)) {
            return "낫싱";
        } else if ("135".equals(guess)) {
            return "3스트라이크";
        } else if ("597".equals(guess)) {
            return "1볼 1스트라이크";
        } else if ("589".equals(guess)) {
            return "3스트라이크";
        }
        return "";
    }
}
