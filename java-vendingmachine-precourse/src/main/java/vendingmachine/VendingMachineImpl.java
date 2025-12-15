package vendingmachine;

import static vendingmachine.Coin.COIN_10;
import static vendingmachine.Coin.COIN_100;
import static vendingmachine.Coin.COIN_50;
import static vendingmachine.Coin.COIN_500;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.Coin.Pocket;
import vendingmachine.util.GlobalExceptions;

public class VendingMachineImpl implements VendingMachine {
    // 지폐 상태로 표현할 수 있는 최소 금액
    private static final int PAPER_MONEY_MIN = 1000;

    private Map<Coin, Integer> vendorBalance = new EnumMap<>(Coin.class);
    private int userBalance = 0;

    private Map<String, Integer> namePriceMap = new HashMap<>();
    private Map<String, Integer> nameQuantityMap = new HashMap<>();

    public VendingMachineImpl() {
        for (Coin coin : Coin.values()) {
            vendorBalance.put(coin, 0);
        }
    }

    @Override
    public void depositMachineBalance(Pocket pocket) {
        pocket.coins().forEach((coin, quantity) -> vendorBalance.merge(coin, quantity, Integer::sum));
    }

    @Override
    public void addMachineStock(Stock stock) {
        // overrides price if name already exists
        namePriceMap.put(stock.name(), stock.price());
        nameQuantityMap.merge(stock.name(), stock.quantity(), Integer::sum);
    }

    @Override
    public void depositUserBalance(int balance) {
        userBalance += balance;
    }

    @Override
    public int getUserBalance() {
        return userBalance;
    }

    @Override
    public boolean canUserPurchase() {
        return namePriceMap.entrySet().stream()
                .filter(e -> e.getValue() <= userBalance) // price <= userBalance
                .anyMatch(e -> nameQuantityMap.get(e.getKey()) > 0); // quantity > 0
    }

    @Override
    public void purchaseProductByName(String name) {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!nameQuantityMap.containsKey(name));
        nameQuantityMap.merge(name, -1, Integer::sum);
        userBalance -= namePriceMap.get(name);
    }
    @Override
    public Pocket withdrawUserChange() {
        var container = new EnumMap<Coin, Integer>(Coin.class);
        // 지폐를 잔돈으로 반환하는 경우는 없다고 가정한다.
        GlobalExceptions.INVALID_STATE.throwsIf(userBalance >= PAPER_MONEY_MIN);

        // desc sorted list 500 100 50 10 (desc sorted)
        var coinAmountTypes = List.of(COIN_500, COIN_100, COIN_50, COIN_10);
        for (var coin : coinAmountTypes) {
            int affordableAmount = Math.min(vendorBalance.get(coin), userBalance / coin.getAmount());
            userBalance -= coin.getAmount() * affordableAmount;
            vendorBalance.merge(coin, -affordableAmount, Integer::sum);
            container.merge(coin, affordableAmount, Integer::sum);
        }
        return new Pocket(container);
    }
}
