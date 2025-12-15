package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private static final Map<Integer, Coin> INTEGER_COIN_MAP = Map.of(
            10, COIN_10, 50, COIN_50, 100, COIN_100, 500, COIN_500
    );
    public static final List<Integer> DESC_SORTED_COINS = List.of(500, 100, 50, 10);
    public static final int MIN_COIN = DESC_SORTED_COINS.getLast();

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    static Coin ofAmount(int amount) {
        return INTEGER_COIN_MAP.get(amount);
    }

    public int getAmount() {
        return amount;
    }

    // coins: [value, quantity]
    public record Pocket(Map<Coin, Integer> coins) {
        public static Pocket ofRandom(int balance) {
            // 주어진 balance 이하의 가치를 갖는 동전 집합 반환
            // 자판기가 보유한 금액은 무작위 함수를 사용해서 동전으로 변환해야 한다
            var container = new EnumMap<Coin, Integer>(Coin.class);
            for (Coin c : Coin.values()) {
                container.put(c, 0);
            }
            while (balance >= 10) {
                int random = Randoms.pickNumberInList(List.of(10, 50, 100, 500));
                if (balance < random) {
                    continue;
                }
                balance -= random;
                container.merge(Coin.ofAmount(random), +1, Integer::sum);
            }
            return new Pocket(container);
        }
    }
}
