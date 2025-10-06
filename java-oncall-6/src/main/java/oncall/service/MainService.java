package oncall.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import oncall.Constant;
import oncall.model.Date;
import oncall.model.Employee;
import oncall.model.in.WorkDate;
import oncall.model.in.WorkOrder;

public class MainService {
    private final WorkDate date;
    private final WorkOrder onWeekDay, onHoliday;

    public MainService(WorkDate date, WorkOrder onWeekDay, WorkOrder onHoliday) {
        this.date = date;
        this.onWeekDay = onWeekDay;
        this.onHoliday = onHoliday;
    }

    public List<Employee> 로직() {
        // 각 일자에 대해
        // 1. 평일이면 평일 근무자 순서, 휴일이면 휴일 근무자 순서를 따라 배정
        // 2. 배정하기 전에 이전 근무자와 같은지 비교. 만약 다르면 계속 진행.
        // 2-1. 같은 경우, 해당 요일이 휴일인 경우, 다음 휴일 근무자와 순서 변경
        // 2-2. 그 외에는 평일 근무자와 순서 변경

        final DayOfWeek start = date.start();
        List<Employee> res = new ArrayList<>();
        List<Employee> onWeekDayOrders = new ArrayList<>(onWeekDay.orders());
        List<Employee> onHolidayOrders = new ArrayList<>(onHoliday.orders());

        int i평일 = 0, i휴일 = 0;

        for (var date : 이번_달_일자()) {
            boolean is휴일 = date.isHoliday() || date.isWeekEnd(start);

            int length = res.size();
            do {
                Employee maybeNext = 다음_근무_후보자(is휴일, i평일, i휴일);

                if (!res.isEmpty() && res.get(res.size() - 1).equals(maybeNext)) {
                    // 교환: 평/휴일 여부를 따져서 WorkOrder 변경
                    if (is휴일) swap(onHolidayOrders, i휴일, i휴일 + 1);
                    if (!is휴일) swap(onWeekDayOrders, i평일, i평일 + 1);
                    continue;
                }
                res.add(maybeNext);
            } while (res.size() != length + 1);
            if (is휴일) ++i휴일;
            if (!is휴일) ++i평일;
        }

        return res;
    }

    private List<Date> 이번_달_일자() {
        final int month = date.month();
        final int nDays = Constant.월별_말일.get(month);

        List<Date> dates = new ArrayList<>();
        for (int day = 1; day <= nDays; ++day) {
            dates.add(new Date(month, day));
        }
        return dates;
    }

    private Employee 다음_근무_후보자(boolean is휴일, int i평일, int i휴일) {
        if (is휴일) return i번_휴일_근무자(i휴일);
        return i번_평일_근무자(i평일);
    }

    private Employee i번_평일_근무자(int i) {
        return onWeekDay.orders().get(i);
    }

    private Employee i번_휴일_근무자(int i) {
        return onHoliday.orders().get(i);
    }

    private void swap(List<Employee> list, int i, int _j) {
        int j = _j % list.size(); // i가 맨 마지막 인덱스인 경우 고려
        var temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
