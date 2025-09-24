package lotto.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoResult;

public class View implements IView {
    private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다.";
    private static final String WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String STATISTICS_MESSAGE = "당첨 통계";
    private static final String STATISTICS_DIVIDER = "---";

    private final BufferedWriter bw;

    public View(BufferedWriter bw) {
        this.bw = bw;
    }

    @Override
    public void printInputMoneyMessage() {
        _write(() -> {
            bw.write(PURCHASE_AMOUNT_MESSAGE);
            _flush();
        });
    }

    @Override
    public void printPurchasedLottos(List<Lotto> lottos) {
        _write(() -> {
            bw.write(String.format(LOTTO_COUNT_MESSAGE_FORMAT, lottos.size()));
            bw.newLine();
            for (var lotto : lottos) {
                bw.write(lotto.toString());
                bw.newLine();
            }
            _flush();
        });
    }

    @Override
    public void printInputWinningNumbersMessage() {
        _write(() -> {
            bw.write(WINNING_NUMBERS_MESSAGE);
            _flush();
        });
    }

    @Override
    public void printInputBonusNumberMessage(List<Integer> winningNumbers) {
        _write(() -> {
            bw.write(BONUS_NUMBER_MESSAGE);
            _flush();
        });
    }

    @Override
    public void printSummary(LottoResult result) {
        _write(() -> {
            bw.write(STATISTICS_MESSAGE);
            bw.newLine();
            bw.write(STATISTICS_DIVIDER);
            bw.newLine();

            var ps = new PrizeSummary(result);
            bw.write(ps.getSummary());
            bw.write(ps.getStats());
            _flush();
        });
    }

    @Override
    public void feedLine() {
        _write(() -> {
            _flush();
        });
    }

    @FunctionalInterface
    private interface IOAction {
        void write() throws IOException;
    }

    private void _write(IOAction action) {
        try {
            action.write();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void _flush() {
        try {
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}