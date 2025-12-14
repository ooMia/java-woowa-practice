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
        // 개수 음수 x
        vendor.addBalance(coins);
    }

    public void supplyStocks(List<Stock> stocks) throws IllegalArgumentException {
        // 상품 이름 같은데 가격 다르면 x
        for (Stock stock : stocks) {
            vendor.addStock(stock);
        }
    }

    // user operations

    public void depositUserBalance(int balance) throws IllegalArgumentException {
        // 음수이면 x
        user.addBalance(balance);
    }

    public int getUserBalance() {
        return user.getBalance();
    }

    public boolean canUserPurchaseSomething(int balance) {
        // 사용자가 상품을 구매할 수 없는 상황이 되면 잔돈을 반환하면 돼
        // 구체화하면 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우야
        return false;
    }

    public void purchase(String itemName) throws IllegalArgumentException {
        // stock 조회
        // 등록 안 된 상품이면 x
        Stock stock = vendor.findStockByName(itemName).orElseThrow(GlobalExceptions.INVALID_ARGUMENTS::exception);

        // 현재 보유 금액으로 못 사면 x
        int balance = user.getBalance();
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(balance < stock.price());

        // 구매
        vendor.popSingleStockByName(itemName);
    }

    public SortedMap<Coin, Integer> withdrawUserBalance() {
        // Operator를 활용하여 테스트하고 구현할 부분
        return null;
    }

    static class Operator {
        // 사용자의 잔액이
    }
}
