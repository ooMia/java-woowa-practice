package calculator.delimiter;

import java.util.List;

class IntegerDefaultDelimiter extends DefaultDelimiter<Integer> {

    @Override
    protected List<String> split(String expression) {
        return List.of(expression.split(DefaultDelimiter.DEFAULT_REGEX));
    }

    @Override
    protected Integer parseNumber(String token) {
        return Integer.parseInt(token);
    }

    @Override
    protected boolean isValid(Integer number) {
        return number > 0;
    }
}
