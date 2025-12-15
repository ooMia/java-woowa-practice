package vendingmachine.scenario;

import java.util.List;
import vendingmachine.vault.Coin;
import vendingmachine.vault.Stock;
import vendingmachine.VendingMachine;
import vendingmachine.util.Console;

public class Scenario extends AbstractScenario {
    public static final int PAPER_MONEY_UNIT = 1_000; // 지폐 최소 단위
    private final VendingMachine machine;

    protected Scenario(Console console, VendingMachine machine) {
        super(console);
        this.machine = machine;
    }

    public static Runnable ofDefault() {
        return new Scenario(camp.nextstep.edu.missionutils.Console::readLine, new VendingMachine());
    }

    @Override
    public void run() {
        initializeMachine();
        userInteractions();
    }

    private void initializeMachine() {
        exceptionHandler.tryUntilValid(() -> {
            int balance = inputView.readVendingMachineBalance();
            Coin.Pocket pocket = Coin.ofRandom(balance);
            machine.supplyCoins(pocket);
            outputView.printVendingMachineBalance(pocket);
        });
        exceptionHandler.tryUntilValid(() -> {
            List<Stock> stocks = inputView.readStocks();
            machine.supplyStocks(stocks);
        });
    }

    private void userInteractions() {
        exceptionHandler.tryUntilValid(() -> {
            int balance = inputView.readUserBalance();
            machine.depositUserBalance(balance);
        });
        purchaseItems();
        Coin.Pocket pocket = machine.withdrawUserBalance();
        outputView.printUserBalanceComplete(pocket);
    }

    private void purchaseItems() {
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
    }
}
