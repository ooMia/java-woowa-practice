package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Game {
    // 1. 입출력 처리
    // 2. 게임 흐름 제어

    final Computer computer = new Computer();

    public void run() {
        while (true) {
            var guess = Console.readLine();
            var res = computer.judge(guess).toString();
            System.out.println(res);

            if ("3스트라이크".equals(res)) {
                // 재시작 처리
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                var input = Console.readLine();

                if (!isRestart(input)) {
                    break;
                } else {
                    computer.restart();
                    continue;
                }
            }
        }
    }

    private boolean isRestart(String input) {
        return switch (input) {
            case "1" -> true;
            case "2" -> false;
            default -> throw new IllegalArgumentException();
        };
    }
}
