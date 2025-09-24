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

    public int inputMoney() {
        view.printInputMoneyMessage();
        int purchaseAmount = tryUntilValid(() -> {
            var res = Reader.readInt();
            Lotto.Validator.validatePrice(res);
            return res;
        });
        postCallHook();
        return purchaseAmount;
    }

    public List<Integer> inputWinningNumbers() {
        view.printInputWinningNumbersMessage();
        List<Integer> winningNumbers = tryUntilValid(() -> {
            var res = Reader.readIntegers(Constant.DELIMITER_WINNING_NUMBERS);
            Lotto.Validator.validateWinningNumbers(res);
            return res;
        });
        postCallHook();
        return winningNumbers;
    }

    public int inputBonusNumber(List<Integer> winningNumbers) {
        view.printInputBonusNumberMessage(winningNumbers);
        int bonusNumber = tryUntilValid(() -> {
            var res = Reader.readInt();
            Lotto.Validator.validateBonusNumber(res, winningNumbers);
            return res;
        });
        postCallHook();
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

    private void postCallHook() {
        view.feedLine();
    }
}
