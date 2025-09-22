package lotto;

import lotto.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        var purchaseAmount = view.step1();
        var lottos = view.step2(purchaseAmount);
        var winningNumbers = view.step3();
        var bonusNumber = view.step4();
        view.step5(purchaseAmount, lottos, winningNumbers, bonusNumber);
    }
}
