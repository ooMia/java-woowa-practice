package oncall.util;

public class Console {
    public static String readLine() {
        return camp.nextstep.edu.missionutils.Console.readLine();
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void print(Object o) {
        print(o.toString());
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void println(Object o) {
        println(o.toString());
    }
}
