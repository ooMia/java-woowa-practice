package lotto.model;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import lotto.util.Constant;

public class PrizeSummary {

    private final int purchaseAmount;
    private final SortedMap<Prize, Integer> rankMap = new TreeMap<>();

    public PrizeSummary(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        this.purchaseAmount = lottos.size() * Constant.LOTTO_PRICE;
        for (var prize : Prize.values()) {
            rankMap.put(prize, 0);
        }

        for (var lotto : lottos) {
            lotto.toPrize(winningNumbers, bonusNumber).ifPresent(prize -> {
                rankMap.put(prize, rankMap.get(prize) + 1);
            });
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
        for (var entry : rankMap.entrySet()) {
            int prize = entry.getKey().getPrize(), count = entry.getValue();
            totalPrize += prize * count;
        }
        double yield = (double) totalPrize / purchaseAmount * 100;
        return String.format("총 수익률은 %,.1f%%입니다.", yield);
    }

    private String formatPrizeSummaryLine(Prize rank, int count) {
        String bonusText = "";
        if (rank.bonusMatched) {
            bonusText = ", 보너스 볼 일치";
        }
        return String.format("%d개 일치%s (%,d원) - %d개", rank.matchCount, bonusText, rank.getPrize(), count);
    }
}