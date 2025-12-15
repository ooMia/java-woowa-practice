package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class CarNameTest {

    private static IntStream validCodes() {
        return IntStream.rangeClosed('a', 'z');
    }

    private static IntStream invalidCodes() {
        // basic latin plane [0x00, 0x7F]
        return IntStream.rangeClosed(0x00, 0x7F)
                .filter(code -> code < 'a' || 'z' < code);
    }

    @ParameterizedTest
    @MethodSource("validCodes")
    void name_withSingleLowerAlpha_notThrows(int code) {
        String name = String.format("%c", code);
        assertDoesNotThrow(() -> new CarName(name));
    }

    @ParameterizedTest
    @MethodSource("invalidCodes")
    void name_containsNonLowerAlphabet_throws(int code) {
        String name = String.format("%c", code);
        assertThrows(IllegalArgumentException.class, () -> new CarName(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void name_nullOrEmpty_throws(String name) {
        assertThrows(IllegalArgumentException.class, () -> new CarName(name));
    }

    @Test
    void name_withLengthFive_notThrows() {
        assertDoesNotThrow(() -> new CarName("short"));
    }

    @Test
    void name_withLengthLongerThanFive_throws() {
        assertThrows(IllegalArgumentException.class, () -> new CarName("longer"));
    }
}
