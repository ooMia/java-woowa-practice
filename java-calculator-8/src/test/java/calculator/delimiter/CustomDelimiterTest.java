package calculator.delimiter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        private static IntStream basicLatin() {
            return IntStream.rangeClosed(0x00, 0x7F) // 128
                    .filter(value -> value != ',' && value != ':')
                    .filter(value -> value < '0' || '9' < value);
        }

        private static IntStream charRangeNumbers() {
            return IntStream.rangeClosed('0', '9');
        }

        private static IntStream emoji() {
            java.util.function.Supplier<Integer> utf8Code = () -> {
                byte[] utf8Bytes = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0xA4, (byte) 0x94};
                // String constructor use default charset UTF-8
                return new String(utf8Bytes).codePointAt(0);
            };

            return IntStream.of("🤔".codePointAt(0), // internally treated as UTF-16
                    "\uD83E\uDD14".codePointAt(0), // UTF-16
                    0x0001_F914, // UTF-32
                    utf8Code.get() // UTF-8
            );
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
        @MethodSource(value = "basicLatin")
        void testParse_function(int code) {
            var expression = String.format("//%c\\n1%c2%c3", code, code, code);
            var actual = delimiter.parse(expression);
            assertThat(actual).isEqualTo(List.of(1, 2, 3));
        }

        @Test
        void testParse_BMPWithoutBasicLatin() {
            var codes = IntStream.rangeClosed(0x80, 0xFFFF);
            codes.forEach(this::testParse_function);
        }

        /**
         * BMP(Basic Multilingual Plane) 외부 문자의 surrogate pair 매칭 케이스
         */
        @ParameterizedTest
        @MethodSource("emoji")
        void testParse_withSurrogatePair(int code) {
            var expression = String.format("//%c\\n1%c2%c3", code, code, code);
            assertEquals("//🤔\\n1🤔2🤔3", expression);
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
