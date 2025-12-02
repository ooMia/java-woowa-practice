package calculator.delimiter;

/**
 * 구분자 - 주어진 문자열을 자신의 구분 규칙에 따라 숫자로 변환한다.
 */
public interface Delimiter<T extends Number> {

    static Delimiter<Integer> ofInteger() {
        return new IntegerCustomDelimiter();
    }

    Iterable<T> parse(String expression);
}
