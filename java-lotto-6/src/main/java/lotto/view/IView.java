package lotto.view;

import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoResult;

public interface IView {
    void printInputMoneyMessage();

    void printPurchasedLottos(List<Lotto> lottos);

    void printInputWinningNumbersMessage();

    void printInputBonusNumberMessage(List<Integer> winningNumbers);

    void printSummary(LottoResult result);

    void feedLine();
}