package vendingmachine;

import java.util.List;

import vendingmachine.util.ExceptionUtil;

public final class Constant {

    public static final Class<IllegalArgumentException> INPUT_EXCEPTION = IllegalArgumentException.class; // #L42
    public static final String ERROR_PREFIX = "[ERROR]"; // #L42
    static {
        ExceptionUtil.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }

    // L#37 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
    public static final int MIN_PRICE = 100, UNIT_PRICE = 10;

    // 음수 처리를 위해 정의
    public static final int MIN_BALANCE = 0;

    // L#130 프로그래밍 요구사항 - Coin 반드시 Coin 클래스 활용
    // L#136 코인 종류 정도?
    // TODO 이것도 enum 활용해서 더 우아하게 표현할 수 있을까?
    public static final List<Integer> COIN_VALUES = List.of(500, 100, 50, 10);

}
