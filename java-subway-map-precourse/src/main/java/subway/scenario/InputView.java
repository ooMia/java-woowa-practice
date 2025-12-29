package subway.scenario;

import subway.util.Console;
import subway.util.ValueRules;

@SuppressWarnings({"ALL", "java:S1068"})
final class InputView {

    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    char readMenuFrom(Character... menus) throws IllegalArgumentException {
        console.printLine("## 원하는 기능을 선택하세요.");
        return readFromList(s -> s.charAt(0), java.util.Arrays.stream(menus).toList());
    }

    String readStringWithMessage(String message) {
        console.printLine(message);
        return readWithValidation(ValueRules.NON_BLANK, String::intern);
    }

    int readIntWithMessage(String message) {
        console.printLine(message);
        return readWithValidation(ValueRules.POSITIVE, Integer::parseInt);
    }

    private <T> T readWithValidation(subway.util.ValueRules.CanValidate<T> validator,
                                     java.util.function.Function<String, T> converter) {
        try {
            String input = console.readLine();
            T value = converter.apply(input);
            return validator.validate(value);
        } catch (subway.util.ValueRules.InvalidValueException e) {
            throw InputExceptions.VALIDATION_FAILED.exception(e);
        } catch (NumberFormatException e) {
            throw InputExceptions.INVALID_FORMAT.exception(e);
        }
    }

    private <T> T readFromList(java.util.function.Function<String, T> converter, java.util.Collection<T> candidates) {
        var validator = ValueRules.isElementOf(candidates.stream().toList());
        return readWithValidation(validator, converter);
    }

    private enum InputExceptions implements subway.util.ExceptionUtils.Problem {
        VALIDATION_FAILED,
        INVALID_FORMAT,
        ;

        @Override
        public String message() {
            return this.name();
        }
    }
}
