package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeSummary {

    private final int purchaseAmount;
    private final List<Prize> prizes = new ArrayList<>();
    private final Map<Prize, Integer> rankMap = new HashMap<>();

    public PrizeSummary(int purchaseAmount, List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        this.purchaseAmount = purchaseAmount;
        for (var lotto : lottos) {
            lotto.toPrize(winningNumbers, bonusNumber).ifPresent(prizes::add);
        }
        for (var prize : Prize.values()) {
            rankMap.put(prize, 0);
        }
        for (var prize : prizes) {
            rankMap.put(prize, rankMap.get(prize) + 1);
        }
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        for (var entry : rankMap.entrySet()) {
            sb.append(formatPrizeSummaryLine(entry.getKey(), entry.getValue())).append("\n");
        }
        return sb.toString();
    }

    public String stats() {
        long totalPrize = 0;
        for (var prize : prizes) {
            totalPrize += prize.getPrize();
        }
        double yield = (double) totalPrize / purchaseAmount * 100;
        return String.format("총 수익률은 %,.1f%%입니다.", yield);
    }

    private String formatPrizeSummaryLine(Prize rank, int count) {
        return String.format("%d개 일치%s (%,d원) - %d개", rank.matchCount, rank.bonusMatched ? ", 보너스 볼 일치" : "", rank
                .getPrize(), count);
    }
}