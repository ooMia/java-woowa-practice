package oncall.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Console {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static String readLine() {
        return camp.nextstep.edu.missionutils.Console.readLine();
    }

    public static void print(String s) {
        try {
            bw.write(s);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void println(String s) {
        try {
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
