package vendingmachine;

import java.util.List;
import java.util.Map;

public class VendingMachine {
    public void supplyCoins(Map<Coin, Integer> coins) throws IllegalArgumentException {
        // 개수 음수 x
    }

    public void supplyStocks(List<Stock> stocks) throws IllegalArgumentException {
        // 상품 이름 같은데 가격 다르면 x
    }

    public boolean canUserPurchase(int balance) {
        return false;
    }

    public void depositUserBalance(int balance) throws IllegalArgumentException {
        // 음수이면 x
    }

    public int getUserBalance() {
        return 0;
    }


    public void purchase(String itemName) throws IllegalArgumentException{
        // 등록 안 된 상품이면 x
        // 현재 보유 금액으로 못 사면 x
    }

    public Map<Coin, Integer> withdrawUserBalance() {
        return null;
    }
}
