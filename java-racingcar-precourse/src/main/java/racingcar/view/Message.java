package racingcar.view;

import java.util.IllegalFormatException;

import racingcar.util.ExceptionHandler;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)
    OUT_GREETING("반갑습니다."),
    OUT_QUESTION_FORMAT("%s의 답은?"),
    IN_INSTRUCTION("입력해주세요: ");

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
