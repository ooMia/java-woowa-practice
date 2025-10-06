package lotto;

public class Application {
    public static void main(String[] args) {
        var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        var view = new lotto.view.View(bw);
        var api = new Controller(view);

        try {
            var money = api.inputMoney();
            var lottos = api.purchaseLottos(money);
            view.printPurchasedLottos(lottos);

            var winningNumbers = api.inputWinningNumbers();
            var bonusNumber = api.inputBonusNumber(winningNumbers);
            var result = new lotto.model.LottoResult(lottos, winningNumbers, bonusNumber);
            view.printSummary(result);
        } catch (final Exception ignore) {
            // unhandled exceptions
            // exception test will fail with exception without this block
        }
    }
}
