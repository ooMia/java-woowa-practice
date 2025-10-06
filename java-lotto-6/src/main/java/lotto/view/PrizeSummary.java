package lotto.view;

import lotto.model.LottoResult;
import lotto.model.Prize;

class PrizeSummary {

    private final LottoResult result;

    PrizeSummary(LottoResult result) {
        this.result = result;
    }

    String getStats() {
        return String.format("총 수익률은 %,.1f%%입니다.", result.getYieldPercentage());
    }

    String getSummary() {
        StringBuilder sb = new StringBuilder();
        Prize[] keyOrder = {Prize.FOURTH, Prize.THIRD, Prize.SECOND, Prize.SECOND_BONUS, Prize.FIRST};
        for (Prize prize : keyOrder) {
            int count = result.getCountForPrize(prize);
            sb.append(formatPrizeLine(prize, count)).append("\n");
        }
        return sb.toString();
    }

    private String formatPrizeLine(Prize prize, int count) {
        String bonusText = "";
        if (prize.bonusMatched) {
            bonusText = ", 보너스 볼 일치";
        }
        return String.format("%d개 일치%s (%,d원) - %d개", prize.matchCount, bonusText, prize.amount, count);
    }
}