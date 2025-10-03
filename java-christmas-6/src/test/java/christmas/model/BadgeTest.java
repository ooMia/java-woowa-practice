package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    void getName_returnsEnumName() {
        assertEquals("별", Badge.별.getName());
        assertEquals("트리", Badge.트리.getName());
        assertEquals("산타", Badge.산타.getName());
    }

    @Test
    void fromTotalBenefit_returnsNull_whenBelowLowestThreshold() {
        assertNull(Badge.fromTotalBenefit(0));
        assertNull(Badge.fromTotalBenefit(4999));
    }

    @Test
    void fromTotalBenefit_returns별_whenAtOrAbove5000Below10000() {
        assertEquals(Badge.별, Badge.fromTotalBenefit(5000));
        assertEquals(Badge.별, Badge.fromTotalBenefit(9999));
    }

    @Test
    void fromTotalBenefit_returns트리_whenAtOrAbove10000Below20000() {
        assertEquals(Badge.트리, Badge.fromTotalBenefit(10000));
        assertEquals(Badge.트리, Badge.fromTotalBenefit(19999));
    }

    @Test
    void fromTotalBenefit_returns산타_whenAtOrAbove20000() {
        assertEquals(Badge.산타, Badge.fromTotalBenefit(20000));
        assertEquals(Badge.산타, Badge.fromTotalBenefit(Integer.MAX_VALUE));
    }

}