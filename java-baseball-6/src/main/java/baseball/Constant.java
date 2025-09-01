package baseball;

public class Constant {

    public static final int NUMBER_LENGTH = 3, MIN_NUMBER = 1, MAX_NUMBER = 9;
    public static final String COMMAND_RESTART = "1", COMMAND_EXIT = "2";
    public static final String WORD_BALL = "볼", WORD_STRIKE = "스트라이크", WORD_NOTHING = "낫싱";

    public static String getGameOverMessage() {
        return String.format("%s개의 숫자를 모두 맞히셨습니다! 게임 종료", NUMBER_LENGTH);
    }

    public static String getRestartMessage() {
        return String.format("게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.", COMMAND_RESTART, COMMAND_EXIT);
    }
}
