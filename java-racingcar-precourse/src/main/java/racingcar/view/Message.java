package racingcar.view;

import java.util.IllegalFormatException;

import racingcar.util.ExceptionHandler;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)

    IN_자동차_이름("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    IN_시도_횟수("시도할 회수는 몇회인가요?"),

    OUT_레이싱_실행_결과_헤더("실행 결과"),
    OUT_상태_출력_FORMAT("%s : %s"), // 자동차 이름, 현재 주행 거리 * '-'
    OUT_최종_우승자_FORMAT("최종 우승자 : %s"), // 한 명이면 이름만, 여러 명이면 사이에 ", "로 구분
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
