package racingcar;

import java.io.BufferedWriter;
import java.io.IOException;

public class View {

    private final BufferedWriter bw;

    public View(BufferedWriter bw) {
        this.bw = bw;
    }

    public void printStatus(racingcar.Game game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printStatus'");
    }

    public void printWinners(racingcar.Game game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printWinners'");
    }

    @FunctionalInterface
    private interface IOAction {
        void write() throws IOException;
    }

    private void _write(IOAction action) {
        try {
            action.write();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void _flush() {
        try {
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
