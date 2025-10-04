package christmas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import christmas.Constant;
import christmas.model.Badge;
import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.MenuCategory;
import christmas.model.Order;
import christmas.model.dto.Benefit;
import christmas.model.dto.BenefitSummary;
import christmas.model.dto.VisitDate;

public class BenefitService {

    private final Order order;
    private final VisitDate visitDate;
    private final List<Benefit> benefits = new ArrayList<>();

    private BenefitSummary summary;

    public BenefitService(Order order, VisitDate visitDate) {
        this.order = order;
        this.visitDate = visitDate;
    }

    public static BenefitService of(Order order, VisitDate visitDate) {
        return new BenefitService(order, visitDate);
    }

    // ------------------------------
    //          요약 객체 생성
    // ------------------------------

    public BenefitSummary toSummary() {
        if (summary != null) return summary;

        Map<Menu, Integer> freebies = calcFreebies();
        int freebiesValue = freebies.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrice() * e.getValue()).sum();

        addBenefits(freebiesValue);
        int totalBenefit = benefits.stream().mapToInt(Benefit::amount).sum();
        int discount = totalBenefit - freebiesValue;
        int finalCost = order.getTotalPrice() - discount;

        Badge badge = Badge.fromTotalBenefit(totalBenefit);
        return summary = new BenefitSummary(freebies, benefits, totalBenefit, finalCost, badge);
    }

    private Map<Menu, Integer> calcFreebies() {
        if (visitDate.isEventDay() && order.getTotalPrice() >= Constant.GIFT_EVENT_MIN_AMOUNT) {
            return Map.of(Menu.샴페인, 1);
        }
        return Map.of();
    }

    // ------------------------------
    //          혜택 계산 로직
    // ------------------------------

    private void addIfPresent(Benefit item) {
        java.util.Optional.ofNullable(item).ifPresent(benefits::add);
    }

    private void addBenefits(int freebiesValue) {
        addIfPresent(calcChristmasDiscount(visitDate)); // 크리스마스 디데이 할인
        if (!visitDate.isEventDay()) return;

        addIfPresent(calcWeekdayDessertDiscount()); // 평일 할인
        addIfPresent(calcWeekendMainDiscount()); // 주말 할인
        addIfPresent(calcSpecialDayDiscount()); // 특별 할인
        addIfPresent(calcFreebieBenefit(freebiesValue)); // 증정 이벤트
    }

    private Benefit calcChristmasDiscount(VisitDate date) {
        if (!date.isChristmasEventDay()) return null;

        int baseDiscount = Constant.CHRISTMAS_DDAY_BASE_DISCOUNT;
        int startDay = Constant.CHRISTMAS_DDAY_START;
        int dailyIncrease = Constant.CHRISTMAS_DDAY_DAILY_INCREASE;
        var discount = baseDiscount + (date.getDay() - startDay) * dailyIncrease;
        return new Benefit(Event.CHRISTMAS_DDAY_DISCOUNT, discount);
    }

    private Benefit calcWeekdayDessertDiscount() {
        if (!visitDate.isWeekDay()) return null;

        int count = 0;
        for (Menu menu : order.getMenus().keySet()) {
            if (MenuCategory.DESSERT.equals(menu.getCategory()))
                count += order.getMenus().get(menu);
        }
        if (count == 0) return null;

        int discount = count * Constant.WEEKDAY_DESSERT_DISCOUNT;
        return new Benefit(Event.WEEKDAY_DISCOUNT, discount);
    }

    private Benefit calcWeekendMainDiscount() {
        if (!visitDate.isWeekend()) return null;

        int count = 0;
        for (Menu menu : order.getMenus().keySet()) {
            if (MenuCategory.MAIN.equals(menu.getCategory()))
                count += order.getMenus().get(menu);
        }
        if (count == 0) return null;

        int discount = count * Constant.WEEKEND_MAIN_DISCOUNT;
        return new Benefit(Event.WEEKEND_DISCOUNT, discount);
    }

    private Benefit calcSpecialDayDiscount() {
        if (!visitDate.isSpecialDay()) return null;
        return new Benefit(Event.SPECIAL_DISCOUNT, Constant.SPECIAL_DAY_DISCOUNT);
    }

    private Benefit calcFreebieBenefit(int freebiesValue) {
        if (freebiesValue == 0) return null;
        return new Benefit(Event.FREEBIE_EVENT, freebiesValue);
    }
}
