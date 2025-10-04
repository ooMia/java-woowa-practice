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

    // 필요하면 정책 변경 또는 외부 주입
    private static final List<BenefitPolicy> policies = List.of(
            new ChristmasDiscountPolicy(),
            new WeekdayDessertDiscountPolicy(),
            new WeekendMainDiscountPolicy(),
            new SpecialDayDiscountPolicy(),
            new FreebieBenefitPolicy()
    );

    public static BenefitSummary toSummary(Order order, VisitDate visitDate) {
        return new BenefitCalculator(policies).getSummary(order, visitDate);
    }

    public static class BenefitCalculator {

        private final List<BenefitPolicy> policies;

        public BenefitCalculator(List<BenefitPolicy> policies) {
            this.policies = policies;
        }

        public BenefitSummary getSummary(Order order, VisitDate visitDate) {
            Map<Menu, Integer> freebies = FreebieBenefitPolicy.calcFreebies(order, visitDate);
            int freebiesValue = FreebieBenefitPolicy.calcFreebiesValue(freebies);

            List<Benefit> benefits = calculateBenefits(order, visitDate);
            int totalBenefit = benefits.stream().mapToInt(Benefit::amount).sum();
            int discount = totalBenefit - freebiesValue;
            int finalCost = order.getTotalPrice() - discount;

            Badge badge = Badge.fromTotalBenefit(totalBenefit);
            return new BenefitSummary(freebies, benefits, totalBenefit, finalCost, badge);
        }

        private List<Benefit> calculateBenefits(Order order, VisitDate visitDate) {
            List<Benefit> result = new ArrayList<>();
            for (BenefitPolicy policy : policies) {
                Benefit benefit = policy.apply(order, visitDate);
                if (benefit != null) result.add(benefit);
            }
            return result;
        }
    }
}

// 할인/증정 정책 인터페이스
interface BenefitPolicy {
    Benefit apply(Order order, VisitDate visitDate);
}

// 크리스마스 디데이 할인 정책
class ChristmasDiscountPolicy implements BenefitPolicy {
    @Override
    public Benefit apply(Order order, VisitDate visitDate) {
        if (!visitDate.isChristmasEventDay()) return null;
        int baseDiscount = Constant.CHRISTMAS_DDAY_BASE_DISCOUNT;
        int startDay = Constant.CHRISTMAS_DDAY_START;
        int dailyIncrease = Constant.CHRISTMAS_DDAY_DAILY_INCREASE;
        int discount = baseDiscount + (visitDate.getDay() - startDay) * dailyIncrease;
        return new Benefit(Event.CHRISTMAS_DDAY_DISCOUNT, discount);
    }
}

// 평일 디저트 할인 정책
class WeekdayDessertDiscountPolicy implements BenefitPolicy {
    @Override
    public Benefit apply(Order order, VisitDate visitDate) {
        if (!visitDate.isWeekDay()) return null;

        int count = 0;
        for (var entry : order.getMenus().entrySet()) {
            if (MenuCategory.DESSERT.equals(entry.getKey().getCategory())) {
                count += entry.getValue();
            }
        }
        if (count == 0) return null;
        int discount = count * Constant.WEEKDAY_DESSERT_DISCOUNT;
        return new Benefit(Event.WEEKDAY_DISCOUNT, discount);
    }
}

// 주말 메인 할인 정책
class WeekendMainDiscountPolicy implements BenefitPolicy {
    @Override
    public Benefit apply(Order order, VisitDate visitDate) {
        if (!visitDate.isWeekend()) return null;

        int count = 0;
        for (var entry : order.getMenus().entrySet()) {
            if (MenuCategory.MAIN.equals(entry.getKey().getCategory())) {
                count += entry.getValue();
            }
        }
        if (count == 0) return null;
        int discount = count * Constant.WEEKEND_MAIN_DISCOUNT;
        return new Benefit(Event.WEEKEND_DISCOUNT, discount);
    }
}

// 특별 할인 정책
class SpecialDayDiscountPolicy implements BenefitPolicy {
    @Override
    public Benefit apply(Order order, VisitDate visitDate) {
        if (!visitDate.isSpecialDay()) return null;
        return new Benefit(Event.SPECIAL_DISCOUNT, Constant.SPECIAL_DAY_DISCOUNT);
    }
}

// 증정 이벤트 정책
class FreebieBenefitPolicy implements BenefitPolicy {
    @Override
    public Benefit apply(Order order, VisitDate visitDate) {
        var freebies = FreebieBenefitPolicy.calcFreebies(order, visitDate);
        var freebiesValue = FreebieBenefitPolicy.calcFreebiesValue(freebies);
        if (freebiesValue == 0) return null;
        return new Benefit(Event.FREEBIE_EVENT, freebiesValue);
    }

    public static Map<Menu, Integer> calcFreebies(Order order, VisitDate visitDate) {
        if (visitDate.isEventDay() && order.getTotalPrice() >= Constant.GIFT_EVENT_MIN_AMOUNT) {
            return Map.of(Menu.샴페인, 1);
        }
        return Map.of();
    }

    public static int calcFreebiesValue(Map<Menu, Integer> freebies) {
        return freebies.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrice() * e.getValue()).sum();
    }
}