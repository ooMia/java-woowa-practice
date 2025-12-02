package calculator.delimiter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class DefaultDelimiterTest {

    private Delimiter<? extends Number> delimiter;

    @Nested
    class IntegerDelimiter {

        @BeforeEach
        void setUp() {
            delimiter = new IntegerDefaultDelimiter();
        }

        @ParameterizedTest
        @NullAndEmptySource
        void testParse_empty(String expression) {
            var actual = delimiter.parse(expression);
            assertThat(actual).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,3", "1,2:3", "1:2:3"})
        void testParse_basic(String expression) {
            var expected = List.of(1, 2, 3);
            var actual = delimiter.parse(expression);
            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1/2,3", "1.2,3", "1,2\\3"})
        void testParse_notNumberThrows(String expression) {
            assertThrows(IllegalArgumentException.class, () -> delimiter.parse(expression));
        }

        @ParameterizedTest
        @ValueSource(strings = {"-1,2,3", "1,-2.3", "1,2,0"})
        void testParse_notPositiveThrows(String expression) {
            assertThrows(IllegalArgumentException.class, () -> delimiter.parse(expression));
        }
    }

    @Nested
    class DoubleDelimiter {

        @BeforeEach
        @SuppressWarnings("deprecation")
        void setUp() {
            delimiter = new DoubleDefaultDelimiter();
        }

        @ParameterizedTest
        @NullAndEmptySource
        void testParse_empty(String expression) {
            var actual = delimiter.parse(expression);
            assertThat(actual).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,3", "1,2:3", "1:2:3"})
        void testParse_basic(String expression) {
            var expected = List.of(1.0, 2.0, 3.0);
            var actual = delimiter.parse(expression);
            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1/2,3", "1+2,3", "1,2\\3"})
        void testParse_notNumberThrows(String expression) {
            assertThrows(IllegalArgumentException.class, () -> delimiter.parse(expression));
        }

        @ParameterizedTest
        @ValueSource(strings = {"-1,2,3", "1,-2.3", "1,2,0"})
        void testParse_notPositiveThrows(String expression) {
            assertThrows(IllegalArgumentException.class, () -> delimiter.parse(expression));
        }
    }
}
