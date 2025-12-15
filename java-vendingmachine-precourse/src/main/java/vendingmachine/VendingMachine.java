package vendingmachine;

import java.util.Map;
import vendingmachine.Coin.Pocket;
import vendingmachine.util.GlobalExceptions;

public class VendingMachine implements IVendingMachine {
    // 지폐 상태로 표현할 수 있는 최소 금액
    private static final int PAPER_MONEY_MIN = 1_000;

    private final Map<Coin, Integer> vendorBalance = new java.util.EnumMap<>(Coin.class);
    private final Map<String, Integer> namePriceMap = new java.util.HashMap<>();
    private final Map<String, Integer> nameQuantityMap = new java.util.HashMap<>();
    private int userBalance = 0;

    public VendingMachine() {
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
        // 지폐를 잔돈으로 반환하는 경우는 없다고 가정한다.
        GlobalExceptions.INVALID_STATE.throwsIf(userBalance >= PAPER_MONEY_MIN);
        var container = new java.util.EnumMap<Coin, Integer>(Coin.class);
        for (int amount : Coin.DESC_SORTED_COINS) {
            Coin coin = Coin.ofAmount(amount);
            int affordableAmount = Math.min(vendorBalance.get(coin), userBalance / amount);
            userBalance -= amount * affordableAmount;
            vendorBalance.merge(coin, -affordableAmount, Integer::sum);
            container.merge(coin, affordableAmount, Integer::sum);
        }
        return new Pocket(container);
    }
}
