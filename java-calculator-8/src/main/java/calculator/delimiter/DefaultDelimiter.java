package calculator.delimiter;

import java.util.Collections;
import java.util.SequencedCollection;

abstract class DefaultDelimiter<T extends Number> implements Delimiter<T> {

    protected static final String DEFAULT_REGEX = "[,:]";

    @Override
    public final Iterable<T> parse(String expression) throws IllegalArgumentException {
        if (expression == null || expression.isEmpty()) {
            return Collections.emptyList();
        }

        var tokens = split(expression);
        return tokens.stream().map(s -> {
            try {
                var token = parseNumber(s);
                if (!isValid(token)) {
                    throw new IllegalArgumentException();
                }
                return token;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }).toList();
    }

    /**
     * 최초 평가식을 개별 토큰으로 분리하는 로직
     *
     * @param expression 최초 평가식
     * @return 순서가 있는 String 컬렉션
     */
    protected abstract SequencedCollection<String> split(String expression);

    /**
     * 최초 평가식에서 구분자로 분리된 이후, 개별 문자열을 숫자로 변환하는 로직
     *
     * @param token 구분자로 구분된 문자열
     * @return 숫자
     */
    protected abstract T parseNumber(String token);

    /**
     * 변환된 개별 숫자에 대한 유효성 검증 로직 정의
     *
     * @param number 숫자
     * @return 숫자의 유효성 (default: true)
     */
    protected boolean isValid(T number) {
        return true;
    }
}
