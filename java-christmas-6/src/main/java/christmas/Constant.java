package christmas;

import java.time.DayOfWeek;
import java.util.Set;

public class Constant {
    // 이벤트 기간
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final int EVENT_START_DAY = 1;
    public static final int EVENT_END_DAY = 31;

    // 이벤트 적용 조건
    public static final int MIN_ORDER_AMOUNT_FOR_EVENT = 10_000;
    public static final boolean ALLOW_BEVERAGE_ONLY_ORDER = false;
    public static final int MAX_MENU_QUANTITY = 20;

    // 크리스마스 디데이 할인
    public static final int CHRISTMAS_DDAY_START = 1;
    public static final int CHRISTMAS_DDAY_END = 25;
    public static final int CHRISTMAS_DDAY_BASE_DISCOUNT = 1_000;
    public static final int CHRISTMAS_DDAY_DAILY_INCREASE = 100;

    // 요일 정의
    public static final Set<DayOfWeek> WEEKDAYS = Set.of(
            DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
    public static final Set<DayOfWeek> WEEKENDS = Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    // 요일별 할인
    public static final int WEEKDAY_DESSERT_DISCOUNT = 2_023;
    public static final int WEEKEND_MAIN_DISCOUNT = 2_023;

    // 특별 할인 및 증정 이벤트
    public static final Set<Integer> SPECIAL_DAYS = Set.of(3, 10, 17, 24, 25, 31);
    public static final int SPECIAL_DAY_DISCOUNT = 1_000;
    public static final int GIFT_EVENT_MIN_AMOUNT = 120_000;

    // 입력 형식 구분자
    public static final String MENU_QUANTITY_SEPARATOR = "-";
    public static final String MENU_ITEM_SEPARATOR = ",";

    // 에러 메시지 접두사
    public static final String ERROR_PREFIX = "[ERROR]";
}