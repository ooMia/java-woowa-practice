package vendingmachine;

import java.util.regex.Pattern;
import vendingmachine.util.GlobalExceptions;
import vendingmachine.util.Parser;

public record Stock(String name, int price, int quantity) {

    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern bracketTrimmer = Pattern.compile("\\[(.+)\\]");
    private static final Parser commaParser = new Parser(',');

    public static Stock of(String input) {
        var matcher = bracketTrimmer.matcher(input);
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!matcher.find());
        var found = matcher.group(1);
        var props = commaParser.parse(found).iterator();
        try {
            // [name,price,quantity]
            return new Stock(props.next(), Integer.parseInt(props.next()), Integer.parseInt(props.next()));
        } catch (NumberFormatException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception();
        }
    }
}
