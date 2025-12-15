package vendingmachine;

import vendingmachine.scenario.Scenario;
import vendingmachine.util.GlobalExceptions;
import vendingmachine.vault.Coin;
import vendingmachine.vault.Coin.Pocket;
import vendingmachine.vault.CoinWallet;
import vendingmachine.vault.DigitalWallet;
import vendingmachine.vault.Inventory;
import vendingmachine.vault.Stock;
import vendingmachine.vault.Wallet;

public class VendingMachine {

    private final Inventory inventory;
    private final Wallet<Coin.Pocket> vendor;
    private final Wallet<Integer> user;

    public VendingMachine() {
        this.inventory = new Inventory();
        this.vendor = new CoinWallet();
        this.user = new DigitalWallet();
    }

    // vendor operations

    public void supplyCoins(Coin.Pocket pocket) {
        vendor.deposit(pocket);
    }

    public void supplyStocks(Iterable<Stock> stocks) {
        stocks.forEach(inventory::addStock);
    }

    // user operations

    public void depositUserBalance(int balance) {
        user.deposit(balance);
    }

    public long getUserBalance() {
        return user.getBalance();
    }

    public boolean canUserPurchaseSomething() {
        // 사용자가 상품을 구매할 수 있으려면 1. 어떤 상품이 존재하고, 2. 사용자 잔액으로 최저 상품을 구매할 수 있어야 해
        return inventory.isStockAvailable() && inventory.findCheapestStock().price() <= user.getBalance();
    }

    public void purchase(String itemName) {
        Stock stock = inventory.findStockByName(itemName); // 존재하지 않는 아이템
        var balance = user.getBalance();
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(balance < stock.price()); // 잔액 부족
        user.withdraw(stock.price());
        inventory.popSingleStockByName(itemName); // 구매 처리
    }

    // DigitalWallet / CoinWallet 사이의 어댑터를 만든다
    public Coin.Pocket withdrawUserBalance() {
        var container = new java.util.EnumMap<Coin, Integer>(Coin.class);
        int userBalance = user.getBalance() % Scenario.PAPER_MONEY_UNIT;
        for (var entry : vendor.getBalance().asDescSortedCoins().entrySet()) {
            var coin = entry.getKey();
            var balanceQuantity = entry.getValue();
            int maxAffordQuantity = Math.min(balanceQuantity, userBalance / coin.getAmount());
            container.put(coin, maxAffordQuantity);
            userBalance -= coin.getAmount() * maxAffordQuantity;
        }
        return new Pocket(container);
    }
}
