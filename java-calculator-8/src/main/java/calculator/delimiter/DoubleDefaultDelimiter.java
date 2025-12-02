package calculator.delimiter;

import java.util.Arrays;
import java.util.List;

class DoubleDefaultDelimiter implements Delimiter<Double> {

    private static final String REGEX = "[,:]";

    @Override
    public Iterable<Double> parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            return List.of();
        }

        var tokens = expression.split(REGEX);
        return Arrays.stream(tokens).map(s -> {
            try {
                var token = Double.parseDouble(s);
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
