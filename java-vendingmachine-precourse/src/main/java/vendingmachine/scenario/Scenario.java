package vendingmachine.scenario;

import java.util.List;
import java.util.SortedMap;
import vendingmachine.Coin;
import vendingmachine.Stock;
import vendingmachine.VendingMachine;
import vendingmachine.util.Console;

public class Scenario extends AbstractScenario {
    private final VendingMachine machine;

    protected Scenario(Console console, VendingMachine machine) {
        super(console);
        this.machine = machine;
    }

    /*
     *  투입 금액으로는 동전을 생성하지 않아
     *  \+ `사용자가 동전을 넣어서 자판기의 상태가 변하는 경우`를 생각하지 말라는 뜻이야.
     *  \+ 자판기의 동전은 최초 초기화 이후로 불변이라 생각하고, `int`로 받아서 산술 연산을 적용하면 돼
     *
     *  상품은 (상품명, 가격)으로 정의되고, 추가적으로 수량 정보가 존재할 수 있어
     *  상품 가격은 100원 이상이고, 10원으로 나누어 떨어져야 해
     *
     *  사용자가 상품을 구매할 수 없는 상황이 되면 잔돈을 반환하면 돼
     *  구체화하면 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우야
     *  \+ 입력받은 상품의 가격이 만원이 넘어가면 자연스럽게 검증 필요한 상황들이 연출될거야
     *
     *  지폐를 잔돈으로 반환하는 경우는 없어
     *  따라서, 동전으로 반환하는 것은 `% 1000` 결과에 대해서만 처리하면 돼
     *  \+ Application에 상수 처리했어
     *
     *  자판기는 잔돈을 처리할 때, 손해보지 않는 선에서 반환할 수 있는 최대 금액을 반환하면 돼
     *  즉, 돈이 없으면 아무것도 반환하지 않으면 되는거지
     *  액수가 큰 동전부터 차례대로 잔돈 반환에 사용한다고 생각하면 편해
     *  또, 반환 후 남은 돈은 자판기에 남아있어야 해
     */
    @Override
    public void run() {
        exceptionHandler.tryUntilValid(() -> {
            int balance = inputView.readVendingMachineBalance();
            SortedMap<Coin, Integer> coins = Coin.ofRandom(balance);
            machine.supplyCoins(coins);
            outputView.printVendingMachineBalance(coins);
        });

        exceptionHandler.tryUntilValid(() -> {
            List<Stock> stocks = inputView.readStocks();
            machine.supplyStocks(stocks);
        });

        exceptionHandler.tryUntilValid(() -> {
            int balance = inputView.readUserBalance();
            machine.depositUserBalance(balance);
        });

        while (true) {
            long balance = machine.getUserBalance();
            outputView.printUserBalanceInProgress(balance);
            if (!machine.canUserPurchaseSomething()) {
                break;
            }
            exceptionHandler.tryUntilValid(() -> {
                String itemName = inputView.readPurchaseItemName();
                machine.purchase(itemName);
            });
        }

        SortedMap<Coin, Integer> balance = machine.withdrawUserBalance();
        outputView.printUserBalanceComplete(balance);
    }
}
