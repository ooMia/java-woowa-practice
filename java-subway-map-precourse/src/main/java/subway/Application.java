package subway;

import java.util.Scanner;
import subway.scenario.RootScenario;
import subway.util.Console;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Console console = scanner::nextLine;
            RootScenario.ofDefault(console).run();
        }
    }
}
