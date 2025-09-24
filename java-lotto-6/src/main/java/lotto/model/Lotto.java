package lotto.model;

import static lotto.util.Constant.LOTTO_MAX_NUMBER;
import static lotto.util.Constant.LOTTO_MIN_NUMBER;
import static lotto.util.Constant.LOTTO_NUMBER_COUNT;
import static lotto.util.Constant.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private void validate(List<Integer> numbers) {
        Validator.validateLottoNumbers(numbers);
    }

    public static List<Lotto> purchase(int money) {
        Validator.validatePrice(money);
        int expected = money / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>(expected);
        while (lottos.size() < expected) {
            try {
                lottos.add(generate());
            } catch (final IllegalArgumentException ignore) {
                // Lotto.generate() might throws unexpected IllegalArgumentException
                // while calling this.validate() in constructor
            }
        }
        return lottos;
    }

    private static Lotto generate() {
        var numbers = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBER_COUNT);
        return new Lotto(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public Optional<Prize> toPrize(List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = 0;
        for (int number : winningNumbers) {
            if (numbers.contains(number)) ++matchCount;
        }
        boolean bonusMatch = numbers.contains(bonusNumber);
        return Optional.ofNullable(Prize.of(matchCount, bonusMatch));
    }

    public class Validator {
        public static void validatePrice(int money) {
            if (money < 0) {
                throw ErrorCode.NEGATIVE_INTEGER_INPUT.toException();
            }
            if (money % LOTTO_PRICE != 0) {
                throw ErrorCode.LOTTO_PRICE_MUST_BE_MULTIPLE.toException();
            }
        }

        public static void validateLottoNumbers(List<Integer> numbers) {
            if (!isLottoSize(numbers.size())) {
                throw ErrorCode.LOTTO_NUMBER_COUNT_MISMATCH.toException();
            }
            
            SortedSet<Integer> uniques = new TreeSet<>(numbers);
            if (!isLottoSize(uniques.size())) {
                throw ErrorCode.LOTTO_NUMBER_DUPLICATE_ERROR.toException();
            }
            if (uniques.first() < LOTTO_MIN_NUMBER || uniques.last() > LOTTO_MAX_NUMBER) {
                throw ErrorCode.LOTTO_NUMBER_OUT_OF_BOUNDS.toException();
            }
        }

        public static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
            if (!isInLottoRange(bonusNumber)) {
                throw ErrorCode.BONUS_NUMBER_OUT_OF_BOUNDS.toException();
            }
            if (winningNumbers.contains(bonusNumber)) {
                throw ErrorCode.BONUS_NUMBER_DUPLICATE_ERROR.toException();
            }
        }

        static boolean isLottoSize(long size) {
            return size == LOTTO_NUMBER_COUNT;
        }

        private static boolean isInLottoRange(int number) {
            return number >= LOTTO_MIN_NUMBER && number <= LOTTO_MAX_NUMBER;
        }
    }
}
