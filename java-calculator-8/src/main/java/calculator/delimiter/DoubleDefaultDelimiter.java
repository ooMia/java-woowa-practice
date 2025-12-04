package calculator.delimiter;

import java.util.List;

/**
 * @deprecated 현재 실수형 연산에 대한 필요가 없어 관리 대상이 아님
 */
@Deprecated(since = "e2b8801b")
@SuppressWarnings({"java:S1133", "DeprecatedIsStillUsed"})
class DoubleDefaultDelimiter extends DefaultDelimiter<Double> {

    @Override
    protected List<String> split(String expression) {
        return List.of(expression.split(DefaultDelimiter.DEFAULT_REGEX));
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
