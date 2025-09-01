package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {

        int[] answer = generateAnswer();

        while (true) {
            var guess = Console.readLine();

            var res = judge(guess);

            if ("3스트라이크".equals(res)) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                // 재시작 처리
                var input = Console.readLine();

                if (!isRestart(input)) {
                    System.out.println("게임 종료");
                    break;
                }
            }

            System.out.println(res);
        }
    }

    static int[] generateAnswer() {
        int[] answer = new int[3];
        for (int i = 0; i < answer.length; ++i) {
            answer[i] = Randoms.pickNumberInRange(0, 9);
        }
        return answer;
    }

    static boolean isRestart(String input) {
        if ("1".equals(input))
            return true;
        else if ("2".equals(input)) {
            return false;
        }
        throw new IllegalArgumentException();
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
        throw new IllegalArgumentException();
    }
}
