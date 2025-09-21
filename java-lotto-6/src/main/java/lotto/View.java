package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;

public class View {
    private Reader reader = new Reader();

    private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String STATISTICS_MESSAGE = "당첨 통계";
    private static final String STATISTICS_DIVIDER = "---";

    public int step1() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
        int purchaseAmount = reader.readInt();
        feedLine();
        return purchaseAmount;
    }

    public List<Lotto> step2(int purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        System.out.println(String.format("%d개를 구매했습니다.", purchaseAmount / Constant.LOTTO_PRICE));
        for (int i = 0; i < purchaseAmount / Constant.LOTTO_PRICE; ++i) {
            var l = Randoms.pickUniqueNumbersInRange(Constant.LOTTO_MIN_NUMBER, Constant.LOTTO_MAX_NUMBER,
                    Constant.LOTTO_NUMBER_COUNT);
            System.out.println(l);
            lottos.add(new Lotto(l));
        }
        feedLine();
        return lottos;
    }

    public List<Integer> step3() {
        System.out.println(WINNING_NUMBERS_MESSAGE);
        List<Integer> winningNumbers = reader.readIntegers(",");
        feedLine();
        return winningNumbers;
    }

    public int step4() {
        System.out.println(BONUS_NUMBER_MESSAGE);
        int bonusNumber = reader.readInt();
        feedLine();
        return bonusNumber;
    }

    public void step5(int purchaseAmount, List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        // TODO 도메인 로직의 비중이 높아보이니 별도의 클래스로 분리하여 구현
        System.out.println(STATISTICS_MESSAGE);
        System.out.println(STATISTICS_DIVIDER);
        List<Prize> ranks = new ArrayList<>();
        for (var lotto : lottos) {
            int matchCount = countMatch(lotto, winningNumbers);
            boolean bonusMatched = lotto.contains(bonusNumber);
            var rank = Prize.of(matchCount, bonusMatched);
            if (rank != null)
                ranks.add(rank);
        }
        System.out.println(buildPrizeSummary(ranks));
        System.out.println(calculateYield(purchaseAmount, ranks));
        feedLine();
    }

    private void feedLine() {
        System.out.println();
    }

    private int countMatch(Lotto lotto, List<Integer> winningNumbers) {
        int count = 0;
        for (int number : winningNumbers) {
            if (lotto.contains(number)) {
                count++;
            }
        }
        return count;
    }

    private String buildPrizeSummary(List<Prize> matches) {
        Map<Prize, Integer> rankCount = new HashMap<>(Map.of(
                Prize.FOURTH, 0,
                Prize.THIRD, 0,
                Prize.SECOND, 0,
                Prize.SECOND_BONUS, 0,
                Prize.FIRST, 0
        ));

        for (var match : matches) {
            rankCount.put(match, rankCount.getOrDefault(match, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (var entry : rankCount.entrySet()) {
            sb.append(formatPrizeSummaryLine(entry.getKey(), entry.getValue())).append("\n");
        }
        return sb.toString();
    }

    private String formatPrizeSummaryLine(Prize rank, int count) {
        return String.format("%d개 일치%s (%,d원) - %d개", rank.matchCount, rank.bonusMatched ? ", 보너스 볼 일치" : "", rank
                .getPrize(), count);
    }

    private String calculateYield(int purchaseAmount, List<Prize> results) {
        long totalPrize = 0;
        for (var result : results) {
            totalPrize += result.getPrize();
        }
        double yield = (double) totalPrize / purchaseAmount * 100;
        return String.format("총 수익률은 %,.1f%%입니다.", yield);
    }
}