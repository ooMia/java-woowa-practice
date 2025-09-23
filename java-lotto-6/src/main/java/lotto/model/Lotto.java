package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.util.Constant;
import lotto.util.ExceptionHandler.ErrorCode;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Lotto> purchase(int money) {
        List<Lotto> lottos = new ArrayList<>(money / Constant.LOTTO_PRICE);
        for (int i = 0; i < money / Constant.LOTTO_PRICE; ++i) {
            var numbers = Randoms.pickUniqueNumbersInRange(
                    Constant.LOTTO_MIN_NUMBER, Constant.LOTTO_MAX_NUMBER, Constant.LOTTO_NUMBER_COUNT);
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
            if (amount % Constant.LOTTO_PRICE != 0) {
                throw ErrorCode.INVALID_LOTTO_PRICE.toException();
            }
        }

        public static void validateWinningNumbers(List<Integer> winningNumbers) {
            if (winningNumbers.size() != Constant.LOTTO_NUMBER_COUNT) {
                throw ErrorCode.INVALID_WINNING_NUMBER_COUNT.toException();
            }
            Set<Integer> numberSet = Set.copyOf(winningNumbers);
            if (numberSet.size() != Constant.LOTTO_NUMBER_COUNT) {
                throw ErrorCode.INVALID_WINNING_NUMBER_DUPLICATE.toException();
            }
            for (int number : winningNumbers) {
                if (number < Constant.LOTTO_MIN_NUMBER || number > Constant.LOTTO_MAX_NUMBER) {
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
            return size == Constant.LOTTO_NUMBER_COUNT;
        }

        private static boolean isInLottoRange(int number) {
            return number >= Constant.LOTTO_MIN_NUMBER && number <= Constant.LOTTO_MAX_NUMBER;
        }
    }
}
