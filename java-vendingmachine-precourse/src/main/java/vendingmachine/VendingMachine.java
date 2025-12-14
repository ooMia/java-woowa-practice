package vendingmachine;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import vendingmachine.util.GlobalExceptions;

public class VendingMachine {

    private final Vault vendor;
    private final Wallet user;

    public VendingMachine() {
        this.vendor = new Vault();
        this.user = new Wallet();
    }

    // vendor operations

    public void supplyCoins(Map<Coin, Integer> coins) throws IllegalArgumentException {
        coins.values().forEach(quantity -> GlobalExceptions.INVALID_ARGUMENTS.throwsIf(quantity < 0));
        vendor.addBalance(coins);
    }

    public void supplyStocks(List<Stock> stocks) throws IllegalArgumentException {
        stocks.forEach(vendor::addStock);
    }

    // user operations

    public void depositUserBalance(int balance) throws IllegalArgumentException {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(balance < 0);
        user.addBalance(balance);
    }

    public long getUserBalance() {
        return user.getBalance();
    }

    public boolean canUserPurchaseSomething() {
        // 사용자가 상품을 구매할 수 있으려면 1. 어떤 상품이 존재하고, 2. 사용자 잔액으로 최저 상품을 구매할 수 있어야 해
        return vendor.isStockAvailable() && vendor.findCheapestStock().price() <= user.getBalance();
    }

    public void purchase(String itemName) throws IllegalArgumentException {
        Stock stock = vendor.findStockByName(itemName)
                .orElseThrow(GlobalExceptions.INVALID_ARGUMENTS::exception); // 존재하지 않는 아이템
        var balance = user.getBalance();
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(balance < stock.price()); // 잔액 부족
        vendor.popSingleStockByName(itemName); // 구매 처리
    }

    public SortedMap<Coin, Integer> withdrawUserBalance() {
        // 사용자의 잔액은 vendor의 잔금을 기준으로 동전의 형태로 인출한다.
        long userBalance = user.getBalance();
        Map<Coin, Integer> result = vendor.withdrawCoins(userBalance);

        // 사용자의 산술적 잔액은 모두 vendor가 가진다. 계좌 이체처럼 보유 동전에는 영향을 미치지 않는다.
        vendor.addBalance(user.withdraw(userBalance));
        return Coin.descSortedCoins(result);
    }
}
