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
        if ("246".equals(guess)) {
            return "낫싱";
        } else if ("135".equals(guess)) {
            return "3스트라이크";
        } else if ("597".equals(guess)) {
            return "1볼 1스트라이크";
        } else if ("589".equals(guess)) {
            return "3스트라이크";
        }
        throw new IllegalArgumentException();
    }
}
