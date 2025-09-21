package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;

public class View {
    private Reader reader = new Reader();

    public int step1() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = reader.readInt();
        System.out.println("");
        return purchaseAmount;
    }

    public List<Lotto> step2(int purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        System.out.println(String.format("%d개를 구매했습니다.", purchaseAmount / 1000));
        for (int i = 0; i < purchaseAmount / 1000; i++) {
            List<Integer> l = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            l.sort(Integer::compareTo);
            lottos.add(new Lotto(l));
            System.out.println(l);
        }
        System.out.println("");
        return lottos;
    }

    public List<Integer> step3() {
        System.out.println("당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = reader.readIntegers(",");
        System.out.println("");
        return winningNumbers;
    }

    public int step4() {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = reader.readInt();
        System.out.println("");
        return bonusNumber;
    }

    public void step5(int purchaseAmount, List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        System.out.println("당첨 통계");
        System.out.println("---");
        List<LottoResult> results = new ArrayList<>();
        for (var lotto : lottos) {
            int matchCount = countMatch(lotto, winningNumbers);
            boolean bonusMatched = lotto.contains(bonusNumber);
            results.add(new LottoResult(matchCount, bonusMatched));

        }
        String result = generateResult(results);
        System.out.println(result);
        String yield = calculateYield(purchaseAmount, results);
        System.out.println(yield);
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

    private record LottoResult(int matchCount, boolean bonusMatched) {
    }

    private String generateResult(List<LottoResult> matches) {
        Map<String, Integer> rankCount = new HashMap<>();
        rankCount.put("3", 0);
        rankCount.put("4", 0);
        rankCount.put("5", 0);
        rankCount.put("5b", 0);
        rankCount.put("6", 0);

        for (var match : matches) {
            String key = switch (match.matchCount) {
                case 3 -> "3";
                case 4 -> "4";
                case 5 -> match.bonusMatched ? "5b" : "5";
                case 6 -> "6";
                default -> null;
            };
            if (key != null) {
                rankCount.put(key, rankCount.getOrDefault(key, 0) + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("3개 일치 (5,000원) - %d개\n", rankCount.get("3")));
        sb.append(String.format("4개 일치 (50,000원) - %d개\n", rankCount.get("4")));
        sb.append(String.format("5개 일치 (1,500,000원) - %d개\n", rankCount.get("5")));
        sb.append(String.format("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", rankCount.get("5b")));
        sb.append(String.format("6개 일치 (2,000,000,000원) - %d개", rankCount.get("6")));
        return sb.toString();
    }

    private String calculateYield(int purchaseAmount, List<LottoResult> results) {
        long totalPrize = 0;
        for (var result : results) {
            totalPrize += switch (result.matchCount) {
                case 3 -> 5000;
                case 4 -> 50000;
                case 5 -> result.bonusMatched ? 30000000 : 1500000;
                case 6 -> 2000000000;
                default -> 0;
            };
        }
        double yield = (double) totalPrize / purchaseAmount * 100;
        return String.format("총 수익률은 %,.1f%%입니다.", yield);
    }
}