package vendingmachine.scenario;

import vendingmachine.Coin.Pocket;
import vendingmachine.util.Console;

@SuppressWarnings({"java:S1144", "ClassCanBeRecord"})
final class OutputView {

    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    void printVendingMachineBalance(Pocket pocket) {
        console.printLine("자판기가 보유한 동전");
        pocket.coins().forEach(this::printCoinQuantity);
    }

    void printUserChange(Pocket pocket) {
        console.printLine("잔돈");
        pocket.coins().forEach((value, quantity) -> {
            if (quantity >= 1) {
                printCoinQuantity(value, quantity);
            }
        });
    }

    private void printCoinQuantity(Integer value, Integer quantity) {
        console.printLine(String.format("%s원 - %s개", value, quantity));
    }

    void printUserBalance(int balance) {
        console.printLine(String.format("투입 금액: %s원", balance));
    }
}
