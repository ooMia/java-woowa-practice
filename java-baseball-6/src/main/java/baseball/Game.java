package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Game {
    // 1. 입출력 처리
    // 2. 게임 흐름 제어

    final Computer computer = new Computer();

    public void run() {
        while (true) {
            var guess = Console.readLine();
            var res = computer.judge(guess);
            System.out.println(res.toString());

            if (res.strike() == Constant.NUMBER_LENGTH) {
                // 재시작 처리
                System.out.println(Constant.getGameOverMessage());
                System.out.println(Constant.getRestartMessage());
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
            case Constant.COMMAND_RESTART -> true;
            case Constant.COMMAND_EXIT -> false;
            default -> throw new IllegalArgumentException();
        };
    }
}
