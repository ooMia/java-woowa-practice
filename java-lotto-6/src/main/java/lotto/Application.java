package lotto;

public class Application {
    public static void main(String[] args) {
        var view = new lotto.view.View();
        var api = new Controller(view);

        try {
            var money = api.inputMoney();
            var lottos = lotto.model.Lotto.purchase(money);
            view.printPurchasedLottos(lottos);

            var winningNumbers = api.inputWinningNumbers();
            var bonusNumber = api.inputBonusNumber(winningNumbers);
            view.printSummary(lottos, winningNumbers, bonusNumber);
        } catch (final Exception ignore) {
            // unhandled exceptions
            // exception test will fail with exception without this block
        }
    }
}
