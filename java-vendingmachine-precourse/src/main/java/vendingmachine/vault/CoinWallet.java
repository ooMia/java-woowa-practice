package vendingmachine.vault;

import java.util.EnumMap;
import java.util.Map;
import vendingmachine.vault.Coin.Pocket;
import vendingmachine.util.GlobalExceptions;

public final class CoinWallet implements Wallet<Pocket> {

    private final Map<Coin, Integer> coinQuantity = new EnumMap<>(Coin.class);

    @Override
    public void deposit(Pocket balance) throws IllegalArgumentException, ArithmeticException {
        balance.coins().forEach((coin, quantity) -> {
            GlobalExceptions.INVALID_ARGUMENTS.throwsIf(quantity < 0);
            this.coinQuantity.merge(coin, quantity, Math::addExact);
        });
    }

    @Override
    public Pocket getBalance() {
        return new Pocket(this.coinQuantity);
    }

    @Override
    public Pocket withdraw(Pocket request) throws IllegalArgumentException, ArithmeticException {
        // 요청한 그대로 인출을 시도하고, 상태가 유효한 경우에만 반환
        request.coins().forEach((coin, quantity) -> {
            GlobalExceptions.INVALID_ARGUMENTS.throwsIf(quantity < 0);
            coinQuantity.merge(coin, quantity, Math::subtractExact);
            GlobalExceptions.INVALID_ARGUMENTS.throwsIf(coinQuantity.get(coin) < 0);
        });
        return request;
    }
}
