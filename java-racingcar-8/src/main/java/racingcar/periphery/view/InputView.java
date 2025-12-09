package racingcar.periphery.view;

import java.util.SequencedCollection;
import racingcar.Console;
import racingcar.util.Parser;

@SuppressWarnings("ClassCanBeRecord")
public final class InputView {
    private static final Parser parser = new Parser(',');
    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    public SequencedCollection<String> readCarNames() {
        console.printLine("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        var carNames = parser.parse(console.readLine());
        validateNames(carNames);
        return carNames;
    }

    private void validateNames(SequencedCollection<String> names) {
        if (names.isEmpty() || names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException();
        }
    }

    public int readGameIteration() {
        console.printLine("시도할 횟수는 몇 회인가요?");
        try {
            var iteration = Integer.parseInt(console.readLine());
            GameIterationRule.POSITIVE.validate(iteration);
            return iteration;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    @SuppressWarnings("SameParameterValue")
    private record GameIterationRule(int minInclusive) {
        static final GameIterationRule POSITIVE = new GameIterationRule(1);

        void validate(int iteration) {
            if (iteration < minInclusive) {
                throw new IllegalArgumentException();
            }
        }
    }
}
