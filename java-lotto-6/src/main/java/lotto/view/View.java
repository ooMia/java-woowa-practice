package lotto.view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.Lotto;
import lotto.model.PrizeSummary;
import lotto.util.Constant;

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
        System.out.println(STATISTICS_MESSAGE);
        System.out.println(STATISTICS_DIVIDER);

        var ps = new PrizeSummary(purchaseAmount, lottos, winningNumbers, bonusNumber);
        System.out.println(ps.summary());
        System.out.println(ps.stats());
        feedLine();
    }

    private void feedLine() {
        System.out.println();
    }
}