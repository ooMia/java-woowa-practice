package baseball.view;

import java.util.IllegalFormatException;

import baseball.util.ExceptionHandler;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)
    OUT_GREETING("숫자 야구 게임을 시작합니다."),

    IN_GUESS_INSTRUCTION("숫자를 입력해주세요 : "),

    OUT_BALL("볼"),
    OUT_STRIKE("스트라이크"),
    OUT_NOTHING("낫싱"),

    OUT_ANSWER_FOUND("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    OUT_ANSWER_FOUND_FORMAT("%s개의 숫자를 모두 맞히셨습니다! 게임 종료"),

    IN_NEW_GAME_QUESTION("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
    IN_NEW_GAME_QUESTION_FORMAT("게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요."),

    // TODO 샘플 코드: 나중에 지우기
    OUT_QUESTION_FORMAT("%s의 답은?"),
    IN_INSTRUCTION("입력해주세요: ");
    ;

    private final String text;

    Message(String text) {
        this.text = text;
    }

    String text() {
        return text;
    }

    String format(Object... args) {
        try {
            return String.format(text, args);
        } catch (IllegalFormatException e) {
            throw ExceptionHandler.exception("Message::format");
        }
    }
}
