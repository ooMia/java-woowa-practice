package lotto.model;

public enum Prize {
    FOURTH(3, false, 5_000),
    THIRD(4, false, 50_000),
    SECOND(5, false, 1_500_000),
    SECOND_BONUS(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    public final int matchCount;
    public final boolean bonusMatched;
    public final int amount;

    Prize(int matchCount, boolean bonusMatched, int amount) {
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
        this.amount = amount;
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
}