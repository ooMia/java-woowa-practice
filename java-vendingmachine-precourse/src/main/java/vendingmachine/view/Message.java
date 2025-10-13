package vendingmachine.view;

import java.util.IllegalFormatException;

import vendingmachine.util.ExceptionUtil;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)

    // 82 자판기가 보유하고 있는 금액을 입력해 주세요.
    // 450
    IN_VENDOR_BALANCE("자판기가 보유하고 있는 금액을 입력해 주세요."),

    // 85 자판기가 보유한 동전
    // 500원 - 0개
    // 100원 - 4개
    // 50원 - 1개
    // 10원 - 0개
    OUT_HEADER_VENDOR_COIN_BALANCE("자판기가 보유한 동전"),

    // 91 상품명과 가격, 수량을 입력해 주세요.
    // [콜라,1500,20];[사이다,1000,10]
    IN_VENDOR_ITEMS("상품명과 가격, 수량을 입력해 주세요."),

    // 94 투입 금액을 입력해 주세요.
    // 3000
    IN_USER_BALANCE("투입 금액을 입력해 주세요."),

    // 97 투입 금액: 3000원
    OUT_HEADER_USER_BALANCE("투입 금액: "),

    // 98 구매할 상품명을 입력해 주세요.
    // 콜라
    IN_USER_ITEM("구매할 상품명을 입력해 주세요."),

    // 106 잔돈
    // 100원 - 4개
    // 50원 - 1개
    OUT_HEADER_USER_CHANGE_COIN_BALANCE("잔돈"),

    // 각각의 코인에 대한 표현은 실제 구현 시에 필요하게 되면 정의
    ;

    private final String text;

    Message(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    String format(Object... args) {
        try {
            return String.format(text, args);
        } catch (IllegalFormatException e) {
            throw ExceptionUtil.unsupported("Message::format", this.name());
        }
    }
}
