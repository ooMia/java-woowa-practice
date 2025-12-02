package calculator.delimiter;

import java.util.Arrays;
import java.util.List;

class IntegerDefaultDelimiter implements Delimiter<Integer> {

    private static final String REGEX = "[,:]";

    @Override
    public Iterable<Integer> parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            return List.of();
        }

        var tokens = expression.split(REGEX);
        return Arrays.stream(tokens).map(s -> {
            try {
                var token = Integer.parseInt(s);
                if (token <= 0) {
                    throw new IllegalArgumentException();
                }
                return token;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }).toList();
    }
}
