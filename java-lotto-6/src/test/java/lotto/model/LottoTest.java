package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1부터 45 사이의 숫자가 아니면 예외가 발생한다.")
    @Test
    void createLottoByOutOfRangeNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 0)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 오름차순으로 정렬되어 저장된다.")
    @Test
    void createLottoByUnorderedNumbers() {
        // when
        Lotto lotto = new Lotto(List.of(5, 3, 1, 4, 2, 6));

        // then
        assertThat(lotto.toString()).isEqualTo(List.of(1, 2, 3, 4, 5, 6).toString());
    }

    @DisplayName("로또를 구매할 때 금액에 맞는 개수의 로또를 생성한다.")
    @Test
    void purchaseLotto() {
        assertThat(Lotto.purchase(0)).hasSize(0);
        assertThat(Lotto.purchase(5_000)).hasSize(5);
    }

    @DisplayName("로또를 구매할 때 금액이 양수가 아니라면 예외가 발생한다.")
    @Test
    void purchaseLottoByInsufficientMoney() {
        assertThatThrownBy(() -> Lotto.purchase(-1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Lotto.purchase(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또를 구매할 때 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void purchaseLottoByNotMultipleOfThousand() {
        assertThatThrownBy(() -> Lotto.purchase(5_500))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Lotto.purchase(500))
                .isInstanceOf(IllegalArgumentException.class);
    }
}