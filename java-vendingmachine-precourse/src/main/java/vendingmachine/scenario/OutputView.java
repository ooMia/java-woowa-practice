package vendingmachine.scenario;

import java.util.SortedMap;
import vendingmachine.Coin;
import vendingmachine.util.Console;

@SuppressWarnings({"ALL", "java:S1068"})
final class OutputView {

    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    void printVendingMachineBalance(SortedMap<Coin, Integer> coins) {
        for (var entry : coins.entrySet()) {
            var amount = entry.getKey().getAmount();
            var number = entry.getValue();
            // 자판기가 보유한 동전은 0개도 출력 (`500원 - 0개, 100원 - 4개, 50원 - 1개, 10원 - 0개`)
            console.printLine(String.format("%s원 - %s개", amount, number));
        }
    }

    void printUserBalanceInProgress(int balance) {
        console.printLine(String.format("투입 금액: %s원", balance));
    }

    void printUserBalanceComplete(SortedMap<Coin, Integer> coins) {
        for (var entry : coins.entrySet()) {
            var amount = entry.getKey().getAmount();
            var number = entry.getValue();
            // 사용자 최종 잔액은 0개 생략 (`100원 - 4개, 50원 - 1개`)
            if (number < 1) {
                continue;
            }
            console.printLine(String.format("%s원 - %s개", amount, number));
        }
    }
}
