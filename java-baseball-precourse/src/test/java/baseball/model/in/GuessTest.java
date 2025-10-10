package baseball.model.in;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class GuessTest {
    @Test
    void test_성공_케이스() {
        int[] input = new int[]{1, 2, 3};
        assertDoesNotThrow(() -> new Guess(input));
        assertArrayEquals(input, new Guess(input).ns());
    }
}
