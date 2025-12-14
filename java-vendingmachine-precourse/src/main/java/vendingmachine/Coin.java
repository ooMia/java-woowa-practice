package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import vendingmachine.util.GlobalExceptions;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static SortedMap<Coin, Integer> ofRandom(int balance) {
        // balance를 상한으로 갖고 최대한 근접한 가치를 갖는 <Coin, 개수> 집합으로 표현
        // 최종 결과는 coin의 가치에 따라 내림차순 정렬된 SortedMap으로 반환
        var descSortedAmount = Comparator.comparingInt(Coin::getAmount).reversed();
        SortedMap<Coin, Integer> container = new TreeMap<>(descSortedAmount);

        var coinAmounts = List.of(10, 50, 100, 500);
        while (balance >= 10) {
            int random = Randoms.pickNumberInList(coinAmounts);
            if (balance < random) {
                continue;
            }
            balance -= random;
            container.merge(Coin.of(random), +1, Integer::sum);
        }
        return container;
    }

    static Coin of(int amount) {
        // 나중에 private Map으로 리팩토링 가능
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

    // 추가 기능 구현
}
