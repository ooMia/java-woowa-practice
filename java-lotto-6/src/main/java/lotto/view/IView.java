package lotto.view;

import java.util.List;

import lotto.model.Lotto;

public interface IView {
    void printInputMoneyMessage();

    void printPurchasedLottos(List<Lotto> lottos);

    void printInputWinningNumbersMessage();

    void printInputBonusNumberMessage(List<Integer> winningNumbers);

    void printSummary(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber);

    void feedLine();
}