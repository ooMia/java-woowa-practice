package lotto;

import java.util.List;
import java.util.function.Supplier;

import lotto.model.Lotto;
import lotto.util.Constant;
import lotto.util.Reader;
import lotto.view.IView;

public class Controller {

    private final IView view;

    public Controller(IView view) {
        this.view = view;
    }

    public void run() {
        // TODO view는 순수하게 출력만 처리해야 하고, 사용자 입력 및 tryUntilValid 로직은 Controller에서 처리되어야 한다
        var money = inputMoney();
        var lottos = Lotto.purchase(money);
        view.printPurchasedLottos(lottos);

        var winningNumbers = inputWinningNumbers();
        var bonusNumber = inputBonusNumber(winningNumbers);
        view.printSummary(lottos, winningNumbers, bonusNumber);
    }

    public int inputMoney() {
        view.printInputMoneyMessage();
        int purchaseAmount = tryUntilValid(() -> {
            var res = Reader.readInt();
            Lotto.Validator.validatePrice(res);
            return res;
        });
        view.feedLine();
        return purchaseAmount;
    }

    public List<Integer> inputWinningNumbers() {
        view.printInputWinningNumbersMessage();
        List<Integer> winningNumbers = tryUntilValid(() -> {
            var res = Reader.readIntegers(Constant.DELIMITER_WINNING_NUMBERS);
            Lotto.Validator.validateWinningNumbers(res);
            return res;
        });
        view.feedLine();
        return winningNumbers;
    }

    public int inputBonusNumber(List<Integer> winningNumbers) {
        view.printInputBonusNumberMessage(winningNumbers);
        int bonusNumber = tryUntilValid(() -> {
            var res = Reader.readInt();
            Lotto.Validator.validateBonusNumber(res, winningNumbers);
            return res;
        });
        view.feedLine();
        return bonusNumber;
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
