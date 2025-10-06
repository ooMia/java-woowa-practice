package oncall.model.in;

import java.time.DayOfWeek;
import java.util.Map;

record WorkDate(int month, DayOfWeek weekDay) {
    private static Map<String, DayOfWeek> map = Map.of(
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

        // TODO: validation
    }

    public static WorkDate of(String line) {
        var args = line.split("\\s*,\\s*");
        return new WorkDate(Integer.parseInt(args[0]), map.get(args[1]));
    }
}
