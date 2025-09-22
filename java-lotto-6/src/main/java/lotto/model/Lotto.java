package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.util.Constant;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        this.numbers.sort(Integer::compareTo);
    }

    public static void validatePrice(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 양수가 아닌 값 " + amount + "이 입력되었습니다.");
        }
        if (amount % Constant.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + Constant.LOTTO_PRICE + "의 배수여야 합니다.");
        }
    }

    public static void validateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != Constant.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        Set<Integer> numberSet = Set.copyOf(winningNumbers);
        if (numberSet.size() != Constant.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
        for (int number : winningNumbers) {
            if (number < Constant.LOTTO_MIN_NUMBER || number > Constant.LOTTO_MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    public static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < Constant.LOTTO_MIN_NUMBER || bonusNumber > Constant.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public static List<Lotto> purchase(int money) {
        List<Lotto> lottos = new ArrayList<>(money / Constant.LOTTO_PRICE);
        for (int i = 0; i < money / Constant.LOTTO_PRICE; ++i) {
            var numbers = Randoms.pickUniqueNumbersInRange(Constant.LOTTO_MIN_NUMBER, Constant.LOTTO_MAX_NUMBER,
                    Constant.LOTTO_NUMBER_COUNT);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public Optional<Prize> toPrize(List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = 0;
        for (int number : winningNumbers) {
            if (numbers.contains(number)) {
                matchCount++;
            }
        }
        boolean bonusMatch = numbers.contains(bonusNumber);
        return Optional.ofNullable(Prize.of(matchCount, bonusMatch));
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != Constant.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> numberSet = Set.copyOf(numbers);
        if (numberSet.size() != Constant.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
        for (int number : numbers) {
            if (number < Constant.LOTTO_MIN_NUMBER || number > Constant.LOTTO_MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

}
