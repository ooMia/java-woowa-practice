package vendingmachine;

import java.util.Map;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Map<Coin, Integer> ofRandom(int balance) {
        return null;
    }

    // 추가 기능 구현
}
