package calculator.delimiter;

import java.util.List;

class DoubleDefaultDelimiter extends DefaultDelimiter<Double> {

    private static final String REGEX = "[,:]";

    @Override
    protected List<String> split(String expression) {
        return List.of(expression.split(REGEX));
    }

    @Override
    protected Double parseNumber(String token) {
        return Double.parseDouble(token);
    }

    @Override
    protected boolean isValid(Double number) {
        return number > 0;
    }
}
