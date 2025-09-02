package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Game {
    // 1. 입출력 처리
    // 2. 게임 흐름 제어

    private final Computer computer = new Computer();
    private Status status = Status.IN_PROGRESS;

    public boolean isRunning() {
        return status != Status.GAME_OVER;
    }

    public Status run() {
        switch (status) {
            case IN_PROGRESS -> {
                var guess = Console.readLine();
                var res = computer.judge(guess);
                System.out.println(res);
                status = res.isAnswer() ? Status.GOT_ANSWER : Status.IN_PROGRESS;
            }

            case GOT_ANSWER -> {
                System.out.println(Constant.getGameOverMessage());
                System.out.println(Constant.getRestartMessage());
                var input = Console.readLine();
                status = isRestart(input) ? Status.NEED_RESET : Status.GAME_OVER;
            }

            case NEED_RESET -> {
                computer.reset();
                status = Status.IN_PROGRESS;
            }

            case GAME_OVER -> {
                status = Status.GAME_OVER;
            }
        }
        return status;
    }

    private boolean isRestart(String input) {
        return switch (input) {
            case Constant.COMMAND_RESTART -> true;
            case Constant.COMMAND_EXIT -> false;
            default -> throw new IllegalArgumentException();
        };
    }

    public enum Status {
        IN_PROGRESS, GOT_ANSWER, NEED_RESET, GAME_OVER
    }
}
