package calculator.delimiter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CustomDelimiterTest {

    private Delimiter<? extends Number> delimiter;

    @Nested
    class IntegerDelimiter {

        private static IntStream charRange() {
            return IntStream.rangeClosed(0x00, 0xFF)
                    .filter(value -> value != ',' && value != ':')
                    .filter(value -> value < '0' || '9' < value);
        }

        private static IntStream charRangeNumbers() {
            return IntStream.rangeClosed('0', '9');
        }

        @BeforeEach
        void setUp() {
            delimiter = new IntegerCustomDelimiter();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"//.\\n"})
        void testParse_empty(String expression) {
            var actual = delimiter.parse(expression);
            assertThat(actual).isEmpty();
        }

        @ParameterizedTest
        @MethodSource(value = "charRange")
        void testParse_basic(int code) {
            var expression = String.format("//%c\\n1%c2%c3", code, code, code);
            var actual = delimiter.parse(expression);
            assertThat(actual).isEqualTo(List.of(1, 2, 3));
        }

        /**
         * UTF16 범위로의 확장성을 설명하는 테스트.<br> `IntStream.rangeClosed(0x00, 0xFFFF)` 범위의 파라미터 테스트도 통과한다.
         */
        @Test
        void testParse_withUTF16() {
            int code = "🤔".codePoints().findFirst().orElseThrow(); // equivalent to "\uD83E\uDD14"
            var expression = String.format("//%c\\n1%c2%c3", code, code, code);
            var actual = delimiter.parse(expression);
            assertThat(actual).isEqualTo(List.of(1, 2, 3));
        }

        @ParameterizedTest
        @MethodSource(value = "charRangeNumbers")
        @ValueSource(ints = {',', ':'})
        void testParse_delimiterWithDefaultThrows(int code) {
            var expression = String.format("//%c\\n1%c2%c3", code, code, code);
            assertThrows(IllegalArgumentException.class, () -> delimiter.parse(expression));
        }
    }
}
