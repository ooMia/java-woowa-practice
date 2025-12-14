package vendingmachine;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, Integer.MAX_VALUE})
    void addBalance(int balance) {
        Assertions.assertDoesNotThrow(() -> wallet.addBalance(balance));
        Assertions.assertEquals(balance, wallet.getBalance());
    }

    @Test
    void addBalanceWithCoins() {
        Map<Coin, Integer> balance = Map.of(
                Coin.COIN_10, 6,
                Coin.COIN_50, 8,
                Coin.COIN_100, 7,
                Coin.COIN_500, 9
        );
        Assertions.assertDoesNotThrow(() -> wallet.addBalance(balance));
        var expected = 10 * 6 + 50 * 8 + 100 * 7 + 500 * 9;
        Assertions.assertEquals(expected, wallet.getBalance());
    }

    @Test
    void withdrawCoins() {
        // 4321
    }

    @Test
    void withdraw() {
    }
}
