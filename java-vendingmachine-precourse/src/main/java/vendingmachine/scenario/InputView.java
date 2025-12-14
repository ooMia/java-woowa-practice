package vendingmachine.scenario;

import java.util.List;
import vendingmachine.Stock;
import vendingmachine.util.Console;
import vendingmachine.util.Parser;

@SuppressWarnings({"ALL", "java:S1068"})
final class InputView {

    private final Parser semicolonParser = new Parser(';');
    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    int readVendingMachineBalance() {
        return 0;
    }

    List<Stock> readStocks() {
        // 상품의 목록은 각각을 대괄호(`[]`)로 묶어 세미콜론(`;`)으로 구분 (`[상품A];[상품B]`)
        // 나머지는 Stock.of를 활용하자
        return semicolonParser.parse(console.readLine(), Stock::of);
    }

    int readUserBalance() {
        return 0;
    }

    String readPurchaseItemName() {
        return "";
    }
}
