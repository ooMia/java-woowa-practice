package christmas.model.dto;

import java.time.LocalDate;

import christmas.Constant;
import christmas.model.ErrorCode;

public class VisitDate {
    private final LocalDate date;
    private final int day;

    public VisitDate(int day) throws IllegalArgumentException {
        if (day < Constant.MONTH_START_DAY || Constant.MONTH_END_DAY < day)
            throw ErrorCode.DATE_NOT_FOUND.exception();
        this.day = day;
        this.date = LocalDate.of(Constant.EVENT_YEAR, Constant.EVENT_MONTH, day);
    }

    public boolean isEventDay() {
        return Constant.EVENT_START_DAY <= day && day <= Constant.EVENT_END_DAY;
    }

    public boolean isChristmasEventDay() {
        return Constant.CHRISTMAS_DDAY_START <= day && day <= Constant.CHRISTMAS_DDAY_END;
    }

    public boolean isWeekend() {
        return Constant.WEEKEND_DAYS.contains(date.getDayOfWeek());
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isSpecialDay() {
        return Constant.SPECIAL_DAYS.contains(day);
    }

    public int getDay() {
        return day;
    }
}
