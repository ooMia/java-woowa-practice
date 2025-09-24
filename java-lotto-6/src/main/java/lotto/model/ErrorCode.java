package lotto.model;

import static lotto.util.Constant.LOTTO_MAX_NUMBER;
import static lotto.util.Constant.LOTTO_MIN_NUMBER;
import static lotto.util.Constant.LOTTO_NUMBER_COUNT;
import static lotto.util.Constant.LOTTO_PRICE;

import lotto.util.ExceptionHandler;

public enum ErrorCode {
    INVALID_LOTTO_PRICE(String.format("구입 금액은 %d의 배수여야 합니다.", LOTTO_PRICE)),
    INVALID_LOTTO_NUMBER_COUNT(String.format("로또 번호는 %d개여야 합니다.", LOTTO_NUMBER_COUNT)),
    INVALID_LOTTO_NUMBER_RANGE(String.format("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)),
    INVALID_LOTTO_NUMBER_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    INVALID_WINNING_NUMBER_COUNT(String.format("당첨 번호는 %d개여야 합니다.", LOTTO_NUMBER_COUNT)),
    INVALID_WINNING_NUMBER_RANGE(String.format("당첨 번호는 %d부터 %d 사이의 숫자여야 합니다.", LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)),
    INVALID_WINNING_NUMBER_DUPLICATE("당첨 번호는 중복될 수 없습니다."),
    INVALID_BONUS_NUMBER_RANGE(String.format("보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.", LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)),
    INVALID_BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    INVALID_INTEGER_INPUT("정수가 아닌 값이 입력되었습니다."),
    INVALID_POSITIVE_INTEGER_INPUT("양수가 아닌 값이 입력되었습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public IllegalArgumentException toException() {
        return ExceptionHandler.exception(this.message);
    }
}