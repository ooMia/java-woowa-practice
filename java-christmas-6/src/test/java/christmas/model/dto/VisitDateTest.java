package christmas.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import christmas.model.ErrorCode;

class VisitDateTest {

    @Test
    void constructor_throwsException_forDayLessThanMinimum() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new VisitDate(0));
        assertEquals(ErrorCode.DATE_NOT_FOUND.exception().getMessage(), ex.getMessage());
    }

    @Test
    void constructor_throwsException_forDayGreaterThanMaximum() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new VisitDate(32));
        assertEquals(ErrorCode.DATE_NOT_FOUND.exception().getMessage(), ex.getMessage());
    }

    // ------------------------------
    //          isEventDay
    // ------------------------------

    @Test
    void isEventDay_returnsTrue_withinEventDayRange() {
        assertTrue(new VisitDate(1).isEventDay());
        assertTrue(new VisitDate(31).isEventDay());
    }

    @Test
    @Disabled("현재 기준으로는 해당 달 전체가 이벤트 기간이라 false인 경우는 없음")
    void isEventDay_returnsFalse_outsideEventDayRange() {
    }

    // ------------------------------
    //      isChristmasEventDay
    // ------------------------------

    @Test
    void isChristmasEventDay_returnsTrue_withinChristmasRange() {
        assertTrue(new VisitDate(1).isChristmasEventDay());
        assertTrue(new VisitDate(25).isChristmasEventDay());
    }

    @Test
    void isChristmasEventDay_returnsFalse_outsideChristmasRange() {
        assertFalse(new VisitDate(26).isChristmasEventDay());
        assertFalse(new VisitDate(31).isChristmasEventDay());
    }

    // ------------------------------
    //      isWeekend / isWeekDay
    // ------------------------------

    @Test
    void isWeekend_and_isWeekDay_workCorrectly() {
        // 금요일과 토요일이 주말
        // 2023년 12월 1일은 금요일, 2일은 토요일
        assertTrue(new VisitDate(1).isWeekend());
        assertTrue(new VisitDate(2).isWeekend());

        // 3일부터 7일까지는 일요일~목요일로 평일
        assertTrue(new VisitDate(3).isWeekDay());
        assertTrue(new VisitDate(4).isWeekDay());
        assertTrue(new VisitDate(5).isWeekDay());
        assertTrue(new VisitDate(6).isWeekDay());
        assertTrue(new VisitDate(7).isWeekDay());
    }

    // ------------------------------
    //        isSpecialDay
    // ------------------------------

    @Test
    void isSpecialDay_returnsTrue_forSpecialDays() {
        // 3, 10, 17, 24, 25, 31일
        assertTrue(new VisitDate(3).isSpecialDay());
        assertTrue(new VisitDate(10).isSpecialDay());
        assertTrue(new VisitDate(17).isSpecialDay());
        assertTrue(new VisitDate(24).isSpecialDay());
        assertTrue(new VisitDate(25).isSpecialDay());
        assertTrue(new VisitDate(31).isSpecialDay());
    }

    @Test
    void isSpecialDay_returnsFalse_forNonSpecialDays() {
        assertFalse(new VisitDate(1).isSpecialDay());
        assertFalse(new VisitDate(4).isSpecialDay());
        assertFalse(new VisitDate(15).isSpecialDay());
        assertFalse(new VisitDate(19).isSpecialDay());
        assertFalse(new VisitDate(27).isSpecialDay());
    }
}