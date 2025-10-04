package christmas.model.dto;

import java.util.Map;

import christmas.model.Badge;
import christmas.model.Menu;

public record BenefitSummary(
        Map<Menu, Integer> freebies,
        Iterable<Benefit> benefits,
        int totalBenefitAmount,
        int finalCost,
        Badge badge) {
}
