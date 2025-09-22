package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lotto.util.Constant;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        this.numbers.sort(Integer::compareTo);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
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
