package vendingmachine;

import java.util.List;
import vendingmachine.Coin.Pocket;

// 내부에 자판기가 보유한 동전과
// 사용자가 투입한 금액에 대한 상태를 보존해야 한다.
// 잔액은 동전으로 반환
public interface VendingMachine {
    void addMachineBalance(Pocket pocket);
    void addMachineStocks(List<Stock> stocks);

    void depositUserBalance(int balance);

    int getUserBalance();

    boolean canUserPurchase();

    void purchaseProductByName(String name);

    // 반환되는 동전이 최소한이 되는 자판기를 구현한다.
    // \+ 자판기는 동전으로 금액을 보유하고, 사용자의 투입 금액은 동전이 아님
    //- 잔돈을 돌려줄 때 현재 보유한 최소 개수의 동전으로 잔돈을 돌려준다.
    //- 지폐를 잔돈으로 반환하는 경우는 없다고 가정한다.
    // \+ 이 함수 실행하는 시점에 지폐, 즉 1000원 이상이 남아있는 건 가정 위반
    //- 잔돈을 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환한다.
    //   - 반환되지 않은 금액은 자판기에 남는다.
    Coin.Pocket withdrawUserChange();

}
