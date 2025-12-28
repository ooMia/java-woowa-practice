package subway;

import java.util.Scanner;
import subway.scenario.MainScenario;
import subway.util.Console;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Console console = new Console() {
                @Override
                public String readLine() {
                    return scanner.nextLine();
                }

                @Override
                public void printLine(String message) {
                    Console.super.printLine("[INFO] " + message);
                }
            };
            MainScenario.of(console).run();
        }
    }
}
