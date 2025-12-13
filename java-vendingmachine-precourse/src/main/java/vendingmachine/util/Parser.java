package vendingmachine.util;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 주어진 구분자를 기준으로 순서를 지키며 토큰을 분할합니다.
 * <pre>{@code
 * var parser = new Parser(',');
 * console.printLine("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
 * var carNames = parser.parse(console.readLine());
 * }</pre>
 * 2개 이상의 구분자도 사용할 수 있습니다. 각각의 구분자를 나열해서 생성하세요.
 * {@snippet :new Parser(',', ':').parse("1,2:3"); // {1, 2, 3} // @highlight regex="'.'" }
 */
@SuppressWarnings("unused")
public final class Parser {
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

    public List<String> parse(String input) {
        if (input.isEmpty()) {
            return List.of();
        }
        checkTrailingDelimiter(input);
        return List.of(pattern.split(input));
    }

    /**
     * 문자열을 구분자 기준으로 토큰화하고, 변환 함수를 적용시켜 반환한다.
     *
     * @param <T>       목표 타입
     * @param input     분리할 문자열
     * @param converter 변환 함수
     * @return 변환된 객체 리스트
     */
    public <T> List<T> parse(String input, java.util.function.Function<String, T> converter) {
        return parse(input).stream().map(converter).toList();
    }

    private void checkTrailingDelimiter(String input) throws IllegalArgumentException {
        var s = String.valueOf(input.charAt(input.length() - 1));
        if (pattern.matcher(s).matches()) {
            throw new IllegalArgumentException();
        }
    }
}
