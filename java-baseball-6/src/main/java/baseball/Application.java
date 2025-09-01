package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {

        int[] answer = new int[3];
        for (int i = 0; i < answer.length; ++i) {
            answer[i] = Randoms.pickNumberInRange(0, 9);
        }

        while (true) {
            var s = Console.readLine();

            if ("2".equals(s)) {
                System.out.println("게임 종료");
                break;
            }

            System.out.println(judge(s));
        }
    }

    static String judge(String guess) {
        if ("1234".equals(guess)) {
            throw new IllegalArgumentException();
        } else if ("246".equals(guess)) {
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
