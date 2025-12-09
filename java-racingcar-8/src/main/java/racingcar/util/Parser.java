package racingcar.util;

import java.util.SequencedCollection;
import java.util.regex.Pattern;

public class Parser {
    private final Pattern pattern;

    public Parser(int... delimiter) {
        String quotes = java.util.Arrays.stream(delimiter)
                .mapToObj(Parser::toRegexQuote)
                .collect(java.util.stream.Collectors.joining());
        pattern = Pattern.compile(String.format("[%s]", quotes));
    }

    private static String toRegexQuote(int c) {
        return Pattern.quote(Character.toString(c));
    }

    public SequencedCollection<String> parse(String input) {
        if (input.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        checkTrailingDelimiter(input);
        return java.util.List.of(pattern.split(input));
    }

    private void checkTrailingDelimiter(String input) {
        var s = String.valueOf(input.charAt(input.length() - 1));
        if (pattern.matcher(s).matches()) {
            throw new IllegalArgumentException();
        }
    }
}
