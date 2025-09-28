package racingcar;

public class View {

    private final java.io.BufferedWriter bw;

    public View(java.io.BufferedWriter bw) {
        this.bw = bw;
    }

    public void println(String message) {
        _write(() -> {
            bw.write(message);
            bw.newLine();
        });
        _flush();
    }

    public void printStatus(racingcar.Car[] cars) {
        for (var car : cars) {
            var status = String.format("%s : %s\n", car.getName(), "-".repeat(car.getPosition()));
            _write(() -> bw.write(status));
        }
        _write(bw::newLine);
        _flush();
    }

    public void printWinners(racingcar.Car[] winners) {
        _write(() -> {
            bw.write(String.format("최종 우승자 : %s", winners[0].getName()));
            for (int i = 1; i < winners.length; ++i) {
                bw.write(", " + winners[i].getName());
            }
        });
        _write(bw::newLine);
        _flush();
    }

    @FunctionalInterface
    private interface IOAction {
        void write() throws java.io.IOException;
    }

    private void _write(IOAction action) {
        try {
            action.write();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void _flush() {
        try {
            bw.flush();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
