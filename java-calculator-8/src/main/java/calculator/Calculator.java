package calculator;

import calculator.delimiter.Delimiter;

public class Calculator {
    private final Delimiter<Integer> delimiter = Delimiter.ofInteger();

    private static int sum(Iterable<Integer> numbers) throws ArithmeticException {
        int sum = 0;
        for (var number : numbers) {
            sum = Math.addExact(sum, number);
        }
        return sum;
    }

    int add(String expression) {
        var numbers = delimiter.parse(expression);
        try {
            return sum(numbers);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException();
        }
    }
}
