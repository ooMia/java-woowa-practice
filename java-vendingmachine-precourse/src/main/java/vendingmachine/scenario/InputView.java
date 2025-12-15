package vendingmachine.scenario;

import java.util.List;
import vendingmachine.Stock;
import vendingmachine.util.Console;
import vendingmachine.util.GlobalExceptions;
import vendingmachine.util.Parser;
import vendingmachine.util.ValueRules;

@SuppressWarnings({"java:S1144", "ClassCanBeRecord"})
final class InputView {

    private final Parser semicolonParser = new Parser(';');
    private final Console console;

    public InputView(Console console) {
        this.console = console;
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

    int readVendingMachineBalance() {
    }

    List<Stock> readVendingMachineStocks() {
return         semicolonParser.parse(readNonBlankString(), Stock::of);
    }
}
