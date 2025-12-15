package vendingmachine.scenario;

import java.util.List;
import vendingmachine.Coin;
import vendingmachine.Coin.Pocket;
import vendingmachine.Stock;
import vendingmachine.VendingMachine;
import vendingmachine.util.Console;

public class Scenario extends AbstractScenario {
    private final VendingMachine vm;

    protected Scenario(Console console) {
        super(console);
        // TODO impl
        vm = null;
    }

    public static Runnable ofDefault() {
        return new Scenario(camp.nextstep.edu.missionutils.Console::readLine);
    }

    @Override
    public void run() {
        exceptionHandler.tryUntilValid(() -> {
            int balance = inputView.readVendingMachineBalance();
            var pocket = Coin.Pocket.ofRandom(balance);
            vm.addMachineBalance(pocket);
            outputView.printVendingMachineBalance(pocket);
        });
        exceptionHandler.tryUntilValid(() -> {
            List<Stock> stocks = inputView.readVendingMachineStocks();
            vm.addMachineStocks(stocks);
        });
        exceptionHandler.tryUntilValid(() -> {
            int balance = inputView.readUserBalance();
            vm.depositUserBalance(balance);
            return balance;
        });
        while (true) {
            int balance = vm.getUserBalance();
            outputView.printUserBalance(balance);
            if (!vm.canUserPurchase()) {
                break;
            }
            exceptionHandler.tryUntilValid(() -> {
                String productName = inputView.readUserPurchaseName();
                vm.purchaseProductByName(productName);
            });
        }
        Pocket userChange = vm.withdrawUserChange();
        outputView.printUserChange(userChange);
    }
}
