package oncall.model.in;

import java.time.DayOfWeek;

import oncall.Constant;
import oncall.ErrorCode;

public record WorkDate(int month, DayOfWeek start) {
    public WorkDate(int month, DayOfWeek start) {
        this.month = month;
        this.start = start;

        if (month < Constant.MIN_MONTH || month > Constant.MAX_MONTH)
            throw ErrorCode.유효하지_않은_입력.exception();
        if (start == null)
            throw ErrorCode.유효하지_않은_입력.exception();
    }

    // helper method for testing
    static WorkDate of(String line) {
        try {
            var args = line.split("\\s*,\\s*");
            var month = Integer.parseInt(args[0]);
            var weekDay = Constant.문자열_요일_맵.get(args[1]);
            return new WorkDate(month, weekDay);
        } catch (Exception e) {
            throw ErrorCode.유효하지_않은_입력.exception();
        }
    }
}
