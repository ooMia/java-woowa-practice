package lotto.model;

import static lotto.util.Constant.LOTTO_PRICE;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final int purchaseAmount;
    private final Map<Prize, Integer> prizeCount;

    public LottoResult(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        this.purchaseAmount = lottos.size() * LOTTO_PRICE;
        this.prizeCount = buildPrizeCountMap(lottos, winningNumbers, bonusNumber);
    }

    private Map<Prize, Integer> buildPrizeCountMap(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        Map<Prize, Integer> result = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            result.put(prize, 0);
        }
        for (Lotto lotto : lottos) {
            lotto.toPrize(winningNumbers, bonusNumber)
                    .ifPresent(prize -> result.put(prize, result.get(prize) + 1));
        }
        return result;
    }

    public int getCountForPrize(Prize prize) {
        return prizeCount.getOrDefault(prize, 0);
    }

    public double getYieldPercentage() {
        return (double) getTotalPrize() / purchaseAmount * 100;
    }

    private long getTotalPrize() {
        return prizeCount.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().amount * entry.getValue())
                .sum();
    }
}