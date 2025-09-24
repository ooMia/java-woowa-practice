package lotto.view;

import static lotto.util.Constant.LOTTO_PRICE;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import lotto.model.Lotto;
import lotto.model.Prize;

class PrizeSummary {

    private final int purchaseAmount;
    private final SortedMap<Prize, Integer> prizeMap = new TreeMap<>();

    PrizeSummary(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        this.purchaseAmount = lottos.size() * LOTTO_PRICE;
        for (var prize : Prize.values()) {
            prizeMap.put(prize, 0);
        }

        for (var lotto : lottos) {
            lotto.toPrize(winningNumbers, bonusNumber).ifPresent(prize -> {
                prizeMap.put(prize, prizeMap.get(prize) + 1);
            });
        }
    }

    String summary() {
        StringBuilder sb = new StringBuilder();
        for (var entry : prizeMap.entrySet()) {
            sb.append(formatPrizeSummaryLine(entry.getKey(), entry.getValue())).append("\n");
        }
        return sb.toString();
    }

    String stats() {
        long totalPrize = 0;
        for (var prize : prizeMap.entrySet()) {
            int amount = prize.getKey().amount, count = prize.getValue();
            totalPrize += amount * count;
        }
        double yield = (double) totalPrize / purchaseAmount * 100;
        return String.format("총 수익률은 %,.1f%%입니다.", yield);
    }

    private String formatPrizeSummaryLine(Prize prize, int count) {
        String bonusText = "";
        if (prize.bonusMatched) {
            bonusText = ", 보너스 볼 일치";
        }
        return String.format("%d개 일치%s (%,d원) - %d개", prize.matchCount, bonusText, prize.amount, count);
    }
}