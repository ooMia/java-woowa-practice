package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
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

    // coins: [value, quantity]
    public record Pocket(Map<Integer, Integer> coins) {
        public static Pocket ofRandom(int balance) {
            // 주어진 balance 이하의 가치를 갖는 동전 집합 반환
            // 자판기가 보유한 금액은 무작위 함수를 사용해서 동전으로 변환해야 한다
            int random = Randoms.pickNumberInList(List.of(10,50,100,500));
            // TODO impl
        }
    }

    // 추가 기능 구현
}
