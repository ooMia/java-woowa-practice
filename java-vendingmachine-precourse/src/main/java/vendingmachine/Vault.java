package vendingmachine;

import java.util.Optional;

class Vault extends Wallet {

    void addStock(Stock stock) {
        // 기존 상품이랑 이름은 같은데 가격 다르면 x
    }

    Optional<Stock> findStockByName(String itemName) {
        return Optional.empty();
    }

    void popSingleStockByName(String itemName) {
    }

    boolean isStockAvailable() {
        return false;
    }

    Stock findCheapestStock() {
        return null;
    }
}
