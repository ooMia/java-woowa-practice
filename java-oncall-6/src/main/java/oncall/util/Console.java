package oncall.util;

import java.io.BufferedWriter;
import java.io.IOException;

public class Console {
    private BufferedWriter bw;

    public Console(BufferedWriter bw) {
        this.bw = bw;
    }

    public String readLine() {
        return camp.nextstep.edu.missionutils.Console.readLine();
    }

    public void print(String s) {
        try {
            bw.write(s);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException("Console::print");
        }
    }

    public void print(Object o) {
        print(o.toString());
    }

    public void println(String s) {
        try {
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException("Console::println");
        }
    }

    public void println(Object o) {
        println(o.toString());
    }
}
