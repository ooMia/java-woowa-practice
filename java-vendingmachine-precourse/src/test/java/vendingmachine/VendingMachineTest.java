package vendingmachine;

import static vendingmachine.Coin.COIN_10;
import static vendingmachine.Coin.COIN_100;
import static vendingmachine.Coin.COIN_50;
import static vendingmachine.Coin.COIN_500;

import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin.Pocket;

class VendingMachineTest {

    private VendingMachine vm;

    // 500, 100, 50, 10 (in order)
    private static Pocket ofPocket(int... coin) {
        var iter = Arrays.stream(coin).iterator();
        return new Pocket(Map.of(COIN_500, iter.nextInt(), COIN_100, iter.nextInt(), COIN_50, iter.nextInt(), COIN_10,
                iter.nextInt()));
    }

    @BeforeEach
    void setUp() {
        vm = new VendingMachineImpl();
    }

    @Test
    void withdrawUserChange() {
        // 500x1 100x1 50x1 10x16 보유
        // 사용자 321원 입금
        // 총 310원 지불 가능

        vm.depositMachineBalance(ofPocket(1, 1, 4, 8));
        vm.depositUserBalance(468);
        var actual = vm.withdrawUserChange();
        var expected = ofPocket(0, 1, 4, 8);
        Assertions.assertEquals(expected, actual);
    }
}
