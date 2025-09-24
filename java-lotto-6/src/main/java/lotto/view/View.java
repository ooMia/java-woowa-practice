package lotto.view;

import java.util.List;

import lotto.model.Lotto;

public class View implements IView {
    private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다.";
    private static final String WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String STATISTICS_MESSAGE = "당첨 통계";
    private static final String STATISTICS_DIVIDER = "---";

    @Override
    public void printInputMoneyMessage() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
    }

    @Override
    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(String.format(LOTTO_COUNT_MESSAGE_FORMAT, lottos.size()));
        for (var lotto : lottos) {
            System.out.println(lotto);
        }
    }

    @Override
    public void printInputWinningNumbersMessage() {
        System.out.println(WINNING_NUMBERS_MESSAGE);
    }

    @Override
    public void printInputBonusNumberMessage(List<Integer> winningNumbers) {
        System.out.println(BONUS_NUMBER_MESSAGE);
    }

    @Override
    public void printSummary(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        System.out.println(STATISTICS_MESSAGE);
        System.out.println(STATISTICS_DIVIDER);

        var ps = new PrizeSummary(lottos, winningNumbers, bonusNumber);
        System.out.println(ps.summary());
        System.out.println(ps.stats());
    }

    @Override
    public void feedLine() {
        System.out.println();
    }
}