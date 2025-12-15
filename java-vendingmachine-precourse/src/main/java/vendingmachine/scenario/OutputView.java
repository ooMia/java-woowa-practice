package vendingmachine.scenario;

import vendingmachine.util.Console;
import vendingmachine.vault.Coin;

@SuppressWarnings({"ALL", "java:S1068"})
final class OutputView {

    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    void printVendingMachineBalance(Coin.Pocket pocket) {
        console.printLine("자판기가 보유한 동전");
        pocket.asDescSortedCoins().forEach(this::printCoinQuantity);
    }

    void printUserBalanceInProgress(long balance) {
        console.printLine(String.format("투입 금액: %s원", balance));
    }

    void printUserBalanceComplete(Coin.Pocket pocket) {
        console.printLine("잔돈");
        pocket.asDescSortedCoins().forEach((coin, quantity) -> {
            if (quantity > 0) { // 사용자 최종 잔액은 0개 생략 (`100원 - 4개, 50원 - 1개`)
                printCoinQuantity(coin, quantity);
            }
        });
    }

    // 자판기가 보유한 동전 출력
    private void printCoinQuantity(Coin coin, Integer quantity) {
        console.printLine(String.format("%s원 - %s개", coin.getAmount(), quantity));
    }
}
