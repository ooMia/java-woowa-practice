package vendingmachine;

import vendingmachine.Coin.Pocket;

// 내부에 자판기가 보유한 동전과
// 사용자가 투입한 금액에 대한 상태를 보존해야 한다.
// 잔액은 동전으로 반환
public interface VendingMachine {
    void depositMachineBalance(Pocket pocket);

    void addMachineStock(Stock stock);

    void depositUserBalance(int balance);

    int getUserBalance();

    boolean canUserPurchase();

    void purchaseProductByName(String name);

    Coin.Pocket withdrawUserChange();
}
