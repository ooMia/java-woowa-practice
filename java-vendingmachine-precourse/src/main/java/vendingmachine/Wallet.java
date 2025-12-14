package vendingmachine;

import java.util.Map;

class Wallet {

    private final Map<Coin, Long> coinBalance = new java.util.EnumMap<>(Coin.class);
    private long digitalBalance = 0;

    void addBalance(Map<Coin, Integer> coins) throws ArithmeticException {
        coins.forEach((key, value) -> coinBalance.merge(key, Long.valueOf(value), Math::addExact));
    }

    void addBalance(int balance) throws ArithmeticException {
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

    Map<Coin, Integer> withdrawCoins(long amount) {

    }

    int withdraw(long amount) {
        return 0;
    }
}
