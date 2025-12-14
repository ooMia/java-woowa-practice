package vendingmachine;

import java.util.Optional;

class Vault extends Wallet {

    void addStock(Stock stock) {
    }

    Optional<Stock> findStockByName(String itemName) {
        return Optional.empty();
    }

    void popSingleStockByName(String itemName) {
    }
}
