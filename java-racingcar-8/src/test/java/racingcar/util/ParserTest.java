package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    @Nested
    class SingleCharParser {

        @ParameterizedTest
        @ValueSource(chars = {',', ' '})
        void testParser(int c) {
            Parser parser = new Parser(c);
            String delimiter = Character.toString(c);
            String input = String.join(delimiter, "a", "b", "c");

            var actual = parser.parse(input);
            var expected = java.util.List.of("a", "b", "c");
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void testParse_withSingleChars() {
            java.util.stream.IntStream.rangeClosed(0x00, 0xFFFF)
                    .filter(value -> !Character.isAlphabetic(value))
                    .forEach(this::testParser);
        }

        @Test
        void testParse_withTrailingDelimiter_throws() {
            Parser parser = new Parser(',');
            String input = "a,b,c,,";
            assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
        }
    }

    @Nested
    class MultiCharParser {

        @Test
        void testParser() {
            Parser parser = new Parser(',', ':');
            String input = "a,b:c";

            var actual = parser.parse(input);
            var expected = java.util.List.of("a", "b", "c");
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void testParse_withTrailingDelimiter_throws() {
            Parser parser = new Parser(',', ':');

            assertThrows(IllegalArgumentException.class, () -> parser.parse("a,b:c,"));
            assertThrows(IllegalArgumentException.class, () -> parser.parse("a,b:c:"));
        }
    }
}
