package vendingmachine.scenario;

import java.util.List;
import vendingmachine.util.Console;
import vendingmachine.util.GlobalExceptions;
import vendingmachine.util.Parser;
import vendingmachine.util.ValueRules;
import vendingmachine.vault.Stock;

@SuppressWarnings({"ALL", "java:S1068", "java:S1144"})
final class InputView {

    private final Parser semicolonParser = new Parser(';');
    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    int readVendingMachineBalance() {
        console.printLine("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return readNonNegativeInt();
    }

    // 입력: "[상품A];[상품B]"
    // 세미콜론(`;`)으로 구분 이후 Stock.of로 변환
    List<Stock> readStocks() {
        console.printLine("상품명과 가격, 수량을 입력해 주세요.");
        return semicolonParser.parse(readNonBlankString(), Stock::of);
    }

    int readUserBalance() {
        console.printLine("투입 금액을 입력해 주세요.");
        return readNonNegativeInt();
    }

    String readPurchaseItemName() {
        console.printLine("구매할 상품명을 입력해 주세요.");
        return readNonBlankString();
    }

    private int readNonNegativeInt() {
        try {
            var number = Integer.parseInt(console.readLine());
            return ValueRules.NON_NEGATIVE.validate(number);
        } catch (NumberFormatException | ValueRules.InvalidValueException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception(e);
        }
    }

    private String readNonBlankString() {
        try {
            return ValueRules.NON_BLANK.validate(console.readLine());
        } catch (ValueRules.InvalidValueException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception(e);
        }
    }
}
