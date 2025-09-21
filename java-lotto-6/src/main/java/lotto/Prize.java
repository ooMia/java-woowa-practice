package lotto;

enum Prize {
    FOURTH(3, false), THIRD(4, false), SECOND(5, false), SECOND_BONUS(5, true), FIRST(6, false);

    final int matchCount;
    final boolean bonusMatched;

    Prize(int matchCount, boolean bonusMatched) {
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
    }

    public static Prize of(int matchCount, boolean bonusMatched) {
        return switch (matchCount) {
            case 3 -> FOURTH;
            case 4 -> THIRD;
            case 5 -> bonusMatched ? SECOND_BONUS : SECOND;
            case 6 -> FIRST;
            default -> null;
        };
    }

    public int getPrize() {
        return switch (this) {
            case FOURTH -> 5_000;
            case THIRD -> 50_000;
            case SECOND -> 1_500_000;
            case SECOND_BONUS -> 30_000_000;
            case FIRST -> 2_000_000_000;
        };
    }
}