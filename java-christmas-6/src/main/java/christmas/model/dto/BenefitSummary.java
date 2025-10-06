package christmas.model.dto;

import java.util.Map;

import christmas.model.Badge;
import christmas.model.Menu;

public record BenefitSummary(
        Map<Menu, Integer> freebies,
        Iterable<Benefit> benefits,
        int totalBenefit,
        int finalCost,
        Badge badge) {
}
