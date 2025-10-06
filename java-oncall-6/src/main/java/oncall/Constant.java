package oncall;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import oncall.model.Date;

public class Constant {
    // #L63 비상 근무자는 평일 순번, 휴일 순번에 각각 1회 편성되어야 한다.
    // #L68 근무자 보호와 비상 근무 운영의 효율을 위해, 비상 근무자는 어떤 경우에도 연속 2일은 근무할 수 없다.

    // notice-1#L2 닉네임은 중복을 허용하지 않음
    // notice-1#L2 닉네임은 최대 5자로 유지
    public static final int 최대_닉네임_길이 = 5;

    // WorkOrder 제약
    // notice-1#L3 최소 5명의 근무자가 유지될 수 있도록 채용
    // notice-1#L4 최대 35명의 근무자를 넘지 않도록 관리
    public static final int MIN_EMPLOYEE = 5, MAX_EMPLOYEE = 35;

    // WorkDate, Date 제약
    public static final int MIN_MONTH = 1, MAX_MONTH = 12;
    public static final int MIN_DAY = 1, MAX_DAY = 31;

    // notice-2#L2 법정 공휴일
    public static final Set<Date> 법정_공휴일 = Set.of(
            new Date(1, 1), new Date(3, 1),
            new Date(5, 5), new Date(6, 6),
            new Date(8, 15), new Date(10, 3),
            new Date(10, 9), new Date(12, 25)
    );


    // #L95 2월은 28일까지만
    public static final List<Integer> 월별_말일 = List.of(null,
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    );

    // #L97 휴일은 토요일, 일요일, 법정공휴일
    public static final Set<DayOfWeek> 휴일_요일 = Set.of(
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
    );

    // #L105 에러
    public static final String ERROR_PREFIX = "[ERROR]";
    // #L109 에러 메시지
    public static final String ERROR_INPUT = "유효하지 않은 입력 값입니다. 다시 입력해 주세요.";

}
