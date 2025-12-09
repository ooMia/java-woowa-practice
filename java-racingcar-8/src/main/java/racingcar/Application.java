package racingcar;

public class Application {

    @SuppressWarnings("java:S1135")
    public static void main(String[] args) {
        new racingcar.periphery.GameScenario(
                new Console(),
                () -> camp.nextstep.edu.missionutils.Randoms.pickNumberInRange(0, 9)
        ).run();
    }
}
