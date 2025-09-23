package lotto.model;

public enum Prize {
    FOURTH(3, false),
    THIRD(4, false),
    SECOND(5, false),
    SECOND_BONUS(5, true),
    FIRST(6, false);

    public final int matchCount;
    public final boolean bonusMatched;

    Prize(int matchCount, boolean bonusMatched) {
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
    }

    public static Prize of(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5) {
            if (bonusMatched) return SECOND_BONUS;
            return SECOND;
        }
        if (matchCount == 4) return THIRD;
        if (matchCount == 3) return FOURTH;
        return null;
    }

    public int getPrize() {
        if (this == FIRST) return 2_000_000_000;
        if (this == SECOND_BONUS) return 30_000_000;
        if (this == SECOND) return 1_500_000;
        if (this == THIRD) return 50_000;
        if (this == FOURTH) return 5_000;
        return 0;
    }
}