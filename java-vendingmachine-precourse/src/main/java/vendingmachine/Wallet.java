package vendingmachine;

import static vendingmachine.Coin.COIN_AMOUNT_TYPES;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

// 인터페이스로 바꾸고
// 구현체 분리해야할듯
class Wallet {

    private final Map<Coin, Long> coinBalance = new EnumMap<>(Coin.class);
    private long digitalBalance = 0;

    void addBalance(Map<Coin, Integer> coins) throws ArithmeticException {
        coins.forEach((key, value) -> coinBalance.merge(key, Long.valueOf(value), Math::addExact));
    }

    void addBalance(long balance) throws ArithmeticException {
        digitalBalance = Math.addExact(digitalBalance, balance);
    }

    long getBalance() throws ArithmeticException {
        long sum = digitalBalance;
        for (var entry : coinBalance.entrySet()) {
            var value = entry.getKey().getAmount();
            var quantity = entry.getValue();

            long adder = Math.multiplyExact(value, quantity);
            sum = Math.addExact(sum, adder);
        }
        return sum;
    }

    // 주어진 수치에 최대한 근접하도록 코인 반환
    Map<Coin, Integer> withdrawCoins(long amount) throws ArithmeticException {
        Map<Coin, Integer> container = new EnumMap<>(Coin.class);
        var descSortedValues = COIN_AMOUNT_TYPES.stream().sorted(Comparator.reverseOrder()).toList();
        for (var value : descSortedValues) {
            int numberToAdd = Math.toIntExact(amount / value);
            if (numberToAdd > 0) {
                long subtracter = Math.multiplyExact(value, numberToAdd);
                amount = Math.subtractExact(amount, subtracter);
                container.put(Coin.of(value), numberToAdd);
            }
        }
        return Collections.unmodifiableMap(container);
    }

    long withdraw(long amount) throws ArithmeticException {
        long result = Math.min(amount, digitalBalance);
        digitalBalance = Math.subtractExact(digitalBalance, result);
        return result;
    }
}
