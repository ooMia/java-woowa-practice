package vendingmachine.vault;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import vendingmachine.util.GlobalExceptions;

public enum Coin {
    COIN_500(500), COIN_100(100), COIN_50(50), COIN_10(10);

    public static final List<Integer> COIN_AMOUNT_TYPES = List.of(10, 50, 100, 500);
    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // balance를 상한으로 갖고 최대한 근접한 가치를 갖는 <Coin, 개수> 집합으로 표현
    // 최종 결과는 coin의 가치에 따라 내림차순 정렬된 SortedMap으로 반환
    public static Coin.Pocket ofRandom(int balance) {
        Map<Coin, Integer> container = new EnumMap<>(Coin.class);
        for (var coin : Coin.values()) {
            container.putIfAbsent(coin, 0);
        }
        while (balance >= 10) {
            int amount = Randoms.pickNumberInList(COIN_AMOUNT_TYPES);
            if (balance < amount) {
                continue;
            }
            balance -= amount;
            container.merge(Coin.of(amount), +1, Integer::sum);
        }
        return new Pocket(container);
    }

    static Coin of(int amount) {
        for (var coin : Coin.values()) {
            if (coin.amount == amount) {
                return coin;
            }
        }
        throw GlobalExceptions.UNSUPPORTED_OPERATION.exception();
    }

    public int getAmount() {
        return amount;
    }

    public record Pocket(Map<Coin, Integer> coins) {
        public Pocket(Map<Coin, Integer> coins) {
            this.coins = Map.copyOf(coins);
        }

        public SortedMap<Coin, Integer> asSortedCoins(Comparator<Coin> comparator) {
            var container = new TreeMap<Coin, Integer>(comparator);
            container.putAll(this.coins);
            return Collections.unmodifiableSortedMap(container);
        }

        public SortedMap<Coin, Integer> asDescSortedCoins() {
            var descending = Comparator.comparingInt(Coin::getAmount).reversed();
            return asSortedCoins(descending);
        }
    }
}
