package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @DisplayName("1등 당첨 시 해당 등급 개수를 정확히 반환한다")
    @Test
    void testGetCountForFirstPrize() {
        // given
        LottoResult result = Fixture.firstPrizeWin();

        // when & then
        assertThat(result.getCountForPrize(Prize.FIRST)).isEqualTo(1);
    }

    @DisplayName("2등(5개 + 보너스) 당첨 시 해당 등급 개수를 정확히 반환한다")
    @Test
    void testGetCountForSecondBonusPrize() {
        // given
        LottoResult result = Fixture.secondBonusPrizeWin();

        // when & then
        assertThat(result.getCountForPrize(Prize.SECOND_BONUS)).isEqualTo(1);
    }

    @DisplayName("복수 등급 당첨 시 각 등급별 개수를 정확히 계산한다")
    @Test
    void testGetCountForMultiplePrizes() {
        // given
        LottoResult result = Fixture.multiplePrizeWin();

        // when & then
        for (Prize prize : Prize.values()) {
            assertThat(result.getCountForPrize(prize)).isEqualTo(1);
        }
    }

    @DisplayName("당첨되지 않은 경우 모든 등급 개수가 0이다")
    @Test
    void testGetCountForNoWin() {
        // given
        LottoResult result = Fixture.noWin();

        // when & then
        for (Prize prize : Prize.values()) {
            assertThat(result.getCountForPrize(prize)).isEqualTo(0);
        }
    }

    @DisplayName("3개 구매 후 1등 1개 당첨 시 수익률을 정확히 계산한다")
    @Test
    void testGetYieldPercentageWithFirstPrize() {
        // given
        LottoResult result = Fixture.firstPrizeWin();

        // when
        double yieldPercentage = result.getYieldPercentage();

        // then - 2,000,000,000원 당첨 / 3,000원 구매 * 100
        assertThat(yieldPercentage).isEqualTo(2_000_000_000.0 / 3_000 * 100);
    }

    @DisplayName("당첨되지 않은 경우 수익률이 0%이다")
    @Test
    void testGetYieldPercentageWithNoWin() {
        // given
        LottoResult result = Fixture.noWin();

        // when
        double yieldPercentage = result.getYieldPercentage();

        // then
        assertThat(yieldPercentage).isEqualTo(0.0);
    }

    @DisplayName("5개 구매 후 각 순위 1개 당첨 시 전체 수익률을 정확히 계산한다")
    @Test
    void testGetYieldPercentageWithMultiplePrizes() {
        // given
        LottoResult result = Fixture.multiplePrizeWin();

        // when
        double yieldPercentage = result.getYieldPercentage();

        // then - 총 2,031,555,000원 당첨 / 5,000원 구매 * 100
        assertThat(yieldPercentage).isEqualTo(2_031_555_000.0 / 5_000 * 100);
    }

    @DisplayName("2개 구매 후 모두 1등이 되어 당첨금이 Integer.MAX_VALUE를 초과해도 수익률을 정확히 계산한다")
    @Test
    void testGetYieldPercentageOverIntegerMax() {
        // given
        LottoResult result = Fixture.overIntegerMaxWin();

        // when
        double yieldPercentage = result.getYieldPercentage();

        // then
        assertThat(yieldPercentage).isEqualTo(6_000_000_000.0 / 3_000 * 100);
    }

    static class Fixture {
        private static final List<Integer> WINNING_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
        private static final int BONUS_NUMBER = 7;

        private static Lotto firstPrizeLotto() {
            return new Lotto(List.of(1, 2, 3, 4, 5, 6));  // 6개 일치
        }

        private static Lotto secondBonusLotto() {
            return new Lotto(List.of(1, 2, 3, 4, 5, 7));  // 5개 + 보너스 일치
        }

        private static Lotto secondLotto() {
            return new Lotto(List.of(1, 2, 3, 4, 5, 8));  // 5개 + 보너스 불일치
        }

        private static Lotto thirdLotto() {
            return new Lotto(List.of(1, 2, 3, 4, 7, 10)); // 4개 일치
        }

        private static Lotto fourthLotto() {
            return new Lotto(List.of(1, 2, 3, 7, 9, 10)); // 3개 일치
        }

        private static Lotto noWinLotto() {
            return new Lotto(List.of(10, 20, 30, 40, 41, 42));
        }

        static LottoResult firstPrizeWin() {
            return createResult(firstPrizeLotto(), noWinLotto(), noWinLotto());
        }

        static LottoResult secondBonusPrizeWin() {
            return createResult(secondBonusLotto(), noWinLotto(), noWinLotto());
        }

        static LottoResult multiplePrizeWin() {
            return createResult(
                    firstPrizeLotto(),
                    secondBonusLotto(),
                    secondLotto(),
                    thirdLotto(),
                    fourthLotto()
            );
        }

        static LottoResult overIntegerMaxWin() {
            return createResult(
                    firstPrizeLotto(),
                    firstPrizeLotto(),
                    firstPrizeLotto()
            );
        }

        static LottoResult noWin() {
            return createResult(noWinLotto(), noWinLotto());
        }

        private static LottoResult createResult(Lotto... lottos) {
            return new LottoResult(List.of(lottos), WINNING_NUMBERS, BONUS_NUMBER);
        }
    }
}