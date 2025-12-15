package racingcar.periphery.view;

import java.util.SequencedCollection;
import racingcar.Console;
import racingcar.car.RacingCar.Status;

@SuppressWarnings("ClassCanBeRecord")
public final class OutputView {
    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    public void printCustomMessage(String message) {
        console.printLine(message);
    }

    public void printStatus(SequencedCollection<Status> stats) {
        stats.stream()
                .map(this::buildSingleStatus)
                .forEach(console::printLine);
        console.printLine("");
    }

    private String buildSingleStatus(Status stat) {
        var dashes = "-".repeat(stat.position());
        return String.format("%s : %s", stat.name(), dashes);
    }

    public void printWinners(SequencedCollection<Status> stats) {
        var names = stats.stream().map(Status::name).toList();
        var joinedNames = String.join(", ", names);
        console.printLine("최종 우승자 : " + joinedNames);
    }
}
