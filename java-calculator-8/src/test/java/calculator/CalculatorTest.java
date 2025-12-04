package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd_basic() {
        String input = "1,2,3";
        assertEquals(6, calculator.add(input));
    }

    @Test
    void testAdd_overflowThrows() {
        String input = String.format("%s,%s", Integer.MAX_VALUE, 1);
        assertThrows(IllegalArgumentException.class, () -> calculator.add(input));
    }
}
