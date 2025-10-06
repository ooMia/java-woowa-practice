package oncall.model;

import java.time.DayOfWeek;

import oncall.Constant;
import oncall.ErrorCode;

public record Date(int month, int day) {

    public Date(int month, int day) {
        this.month = month;
        this.day = day;

        if (month < Constant.MIN_MONTH || month > Constant.MAX_MONTH)
            throw ErrorCode.인자_오류.assertion();
        if (day < Constant.MIN_DAY || day > Constant.MAX_DAY)
            throw ErrorCode.인자_오류.assertion();
    }

    public DayOfWeek toDayOfWeek(DayOfWeek monthFirst) {
        return monthFirst.plus(day - 1);
    }

    public boolean isWeekEnd(DayOfWeek monthFirst) {
        return Constant.휴일_요일.contains(toDayOfWeek(monthFirst));
    }

    public boolean isHoliday() {
        return Constant.법정_공휴일.contains(this);
    }
}
