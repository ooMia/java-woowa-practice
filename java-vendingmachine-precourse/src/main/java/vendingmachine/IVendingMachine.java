package vendingmachine;

import vendingmachine.Coin.Pocket;

public interface IVendingMachine {
    void depositMachineBalance(Pocket pocket);

    void addMachineStock(Stock stock);

    void depositUserBalance(int balance);

    int getUserBalance();

    boolean canUserPurchase();

    void purchaseProductByName(String name);

    Coin.Pocket withdrawUserChange();
}
