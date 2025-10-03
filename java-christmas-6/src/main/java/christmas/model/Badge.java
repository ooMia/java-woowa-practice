package christmas.model;

import java.util.Arrays;
import java.util.List;

public enum Badge {
    별(5_000), 트리(10_000), 산타(20_000);

    private final String name;
    private final int value;

    Badge(int value) {
        this.name = this.name();
        this.value = value;
    }

    private static final List<Badge> badgeByDescValue = Arrays.stream(Badge.values())
            .sorted((b1, b2) -> Integer.compare(b2.value, b1.value)).toList();

    // 총 혜택 금액에 따른 배지 조회
    public static Badge fromTotalBenefit(int totalBenefit) {
        for (var badge : badgeByDescValue) {
            if (totalBenefit >= badge.value) {
                return badge;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
