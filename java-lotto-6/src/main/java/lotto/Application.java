package lotto;

import lotto.view.View;

public class Application {
    public static void main(String[] args) {
        try {
            View view = new View();
            var money = view.step1();
            var lottos = view.step2(money);
            var winningNumbers = view.step3();
            var bonusNumber = view.step4(winningNumbers);
            view.step5(lottos, winningNumbers, bonusNumber);
        } catch (final Exception ignore) {
            // unhandled exceptions
        }
    }
}
