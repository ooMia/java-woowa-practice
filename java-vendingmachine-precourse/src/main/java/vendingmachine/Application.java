package vendingmachine;

import vendingmachine.scenario.Scenario;

public class Application {
    public static void main(String[] args) {
        Scenario.ofDefault().run();
    }
}
