package subway.scenario;

import subway.util.Console;
import subway.util.ExceptionUtils.Problem;
import subway.util.ValueRules;
import subway.util.ValueRules.InvalidValueException;

@SuppressWarnings({"ALL", "java:S1068"})
final class InputView {

    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    char readMenuFrom(char... menus) throws IllegalArgumentException {
        console.printLine("## 원하는 기능을 선택하세요.");
        char menu = readMenu();
        for (char menuItem : menus) {
            if (menu == menuItem) {
                return menu;
            }
        }
        throw InputExceptions.MENU_NOT_AVAILABLE.exception();
    }

    String readStringWithMessage(String message) {
        console.printLine(message);
        return readNonBlank();
    }

    int readIntWithMessage(String message) {
        console.printLine(message);
        return readPositiveInt();
    }

    private char readMenu() throws IllegalArgumentException {
        try {
            return ValueRules.SINGLE_CHAR.validate(console.readLine()).charAt(0);
        } catch (InvalidValueException e) {
            throw InputExceptions.NON_SINGLE_CHAR.exception(e);
        }
    }

    private String readNonBlank() {
        try {
            return ValueRules.NON_BLANK.validate(console.readLine());
        } catch (InvalidValueException e) {
            throw InputExceptions.BLANK_INPUT.exception(e);
        }
    }

    private int readPositiveInt() {
        try {
            int number = Integer.parseInt(console.readLine());
            return ValueRules.POSITIVE.validate(number);
        } catch (NumberFormatException e) {
            throw InputExceptions.INVALID_FORMAT.exception(e);
        } catch (InvalidValueException e) {
            throw InputExceptions.NON_POSITIVE_INT.exception(e);
        }
    }

    private enum InputExceptions implements Problem {
        NON_POSITIVE_INT,
        BLANK_INPUT, MENU_NOT_AVAILABLE, NON_SINGLE_CHAR, INVALID_FORMAT;

        @Override
        public String message() {
            return this.name();
        }
    }
}
