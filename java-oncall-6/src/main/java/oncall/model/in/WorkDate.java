package oncall.model.in;

import java.time.DayOfWeek;
import java.util.Map;

import oncall.Constant;
import oncall.ErrorCode;

record WorkDate(int month, DayOfWeek weekDay) {
    private static Map<String, DayOfWeek> weekDayMap = Map.of(
            "월", DayOfWeek.MONDAY,
            "화", DayOfWeek.TUESDAY,
            "수", DayOfWeek.WEDNESDAY,
            "목", DayOfWeek.THURSDAY,
            "금", DayOfWeek.FRIDAY,
            "토", DayOfWeek.SATURDAY,
            "일", DayOfWeek.SUNDAY
    );

    WorkDate(int month, DayOfWeek weekDay) {
        this.month = month;
        this.weekDay = weekDay;

        if (month < Constant.MIN_MONTH || month > Constant.MAX_MONTH)
            throw ErrorCode.유효하지_않은_입력.exception();
        if (weekDay == null)
            throw ErrorCode.유효하지_않은_입력.exception();
    }

    public static WorkDate of(String line) {
        try {
            var args = line.split("\\s*,\\s*");
            var month = Integer.parseInt(args[0]);
            var weekDay = weekDayMap.get(args[1]);
            return new WorkDate(month, weekDay);
        } catch (Exception e) {
            throw ErrorCode.유효하지_않은_입력.exception();
        }
    }
}
