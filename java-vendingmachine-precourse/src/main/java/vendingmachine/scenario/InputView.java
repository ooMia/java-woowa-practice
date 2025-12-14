package vendingmachine.scenario;

import java.util.List;
import vendingmachine.Stock;
import vendingmachine.util.Console;

@SuppressWarnings({"ALL", "java:S1068"})
final class InputView {

    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    int readVendingMachineBalance() {
        return 0;
    }

    List<Stock> readStocks() {
        // 상품의 목록은 각각을 대괄호(`[]`)로 묶어 세미콜론(`;`)으로 구분 (`[상품A];[상품B]`)
        // 각 상품의 속성(상품명, 가격, 수량)은 대괄호를 제외하고, 쉼표(`,`)로 구분 (`[콜라,1500,20]`)
        return null;
    }

    int readUserBalance() {
        return 0;
    }

    String readPurchaseItemName() {
        return "";
    }
}
