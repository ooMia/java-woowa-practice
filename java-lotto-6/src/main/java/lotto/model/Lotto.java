package lotto.model;

import static lotto.util.Constant.LOTTO_MAX_NUMBER;
import static lotto.util.Constant.LOTTO_MIN_NUMBER;
import static lotto.util.Constant.LOTTO_NUMBER_COUNT;
import static lotto.util.Constant.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public static List<Lotto> purchase(int money) {
        int expected = money / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>(expected);
        while (lottos.size() < expected) {
            try {
                lottos.add(generate());
            } catch (final IllegalArgumentException ignore) {
                // Lotto.generate() might throws IllegalArgumentException
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

    private void validate(List<Integer> numbers) {
        if (!Validator.isLottoSize(numbers.size())) {
            throw ErrorCode.INVALID_LOTTO_NUMBER_COUNT.toException();
        }
        if (!Validator.isLottoSize(Set.copyOf(numbers).size())) {
            throw ErrorCode.INVALID_LOTTO_NUMBER_DUPLICATE.toException();
        }
        for (int number : numbers) {
            if (!Validator.isInLottoRange(number)) {
                throw ErrorCode.INVALID_LOTTO_NUMBER_RANGE.toException();
            }
        }
    }

    public class Validator {
        public static void validatePrice(int amount) {
            if (amount <= 0) {
                throw ErrorCode.INVALID_POSITIVE_INTEGER_INPUT.toException();
            }
            if (amount % LOTTO_PRICE != 0) {
                throw ErrorCode.INVALID_LOTTO_PRICE.toException();
            }
        }

        public static void validateWinningNumbers(List<Integer> winningNumbers) {
            if (!isLottoSize(winningNumbers.size())) {
                throw ErrorCode.INVALID_WINNING_NUMBER_COUNT.toException();
            }
            Set<Integer> uniques = Set.copyOf(winningNumbers);
            if (!isLottoSize(uniques.size())) {
                throw ErrorCode.INVALID_WINNING_NUMBER_DUPLICATE.toException();
            }
            for (int number : winningNumbers) {
                if (!isInLottoRange(number)) {
                    throw ErrorCode.INVALID_WINNING_NUMBER_RANGE.toException();
                }
            }
        }

        public static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
            if (!isInLottoRange(bonusNumber)) {
                throw ErrorCode.INVALID_BONUS_NUMBER_RANGE.toException();
            }
            if (winningNumbers.contains(bonusNumber)) {
                throw ErrorCode.INVALID_BONUS_NUMBER_DUPLICATE.toException();
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
