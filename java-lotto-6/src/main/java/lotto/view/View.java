package lotto.view;

import java.util.List;
import java.util.function.Supplier;

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

    public Integer step1() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
        Integer purchaseAmount = tryUntilValid(() -> {
            var res = reader.readInt();
            Lotto.validatePrice(res);
            return res;
        });
        feedLine();
        return purchaseAmount;
    }

    public List<Lotto> step2(Integer money) {
        var lottos = Lotto.purchase(money);
        System.out.println(String.format("%d개를 구매했습니다.", money / Constant.LOTTO_PRICE));
        for (var lotto : lottos) {
            System.out.println(lotto);
        }
        feedLine();
        return lottos;
    }

    public List<Integer> step3() {
        System.out.println(WINNING_NUMBERS_MESSAGE);
        List<Integer> winningNumbers = tryUntilValid(() -> {
            var res = reader.readIntegers(Constant.DELIMITER_WINNING_NUMBERS);
            Lotto.validateWinningNumbers(res);
            return res;
        });
        feedLine();
        return winningNumbers;
    }

    public Integer step4(List<Integer> winningNumbers) {
        System.out.println(BONUS_NUMBER_MESSAGE);
        Integer bonusNumber = tryUntilValid(() -> {
            var res = reader.readInt();
            Lotto.validateBonusNumber(res, winningNumbers);
            return res;
        });
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

    private <T> T tryUntilValid(Supplier<T> func) {
        while (true) {
            try {
                return func.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}