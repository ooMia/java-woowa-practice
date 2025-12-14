package vendingmachine.scenario;

import java.util.Map;
import vendingmachine.Coin;
import vendingmachine.util.Console;

@SuppressWarnings({"ALL", "java:S1068"})
final class OutputView {

    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    void printVendingMachineBalance(Map<Coin, Integer> coins) {
        // 자판기가 보유한 동전은 0개도 출력 (`500원 - 0개, 100원 - 4개, 50원 - 1개, 10원 - 0개`)
    }

    void printUserBalanceInProgress(int balance) {
    }


    void printUserBalanceComplete(Map<Coin, Integer> balance) {
        // 사용자 최종 잔액은 0개 생략 (`100원 - 4개, 50원 - 1개`)
    }
}
