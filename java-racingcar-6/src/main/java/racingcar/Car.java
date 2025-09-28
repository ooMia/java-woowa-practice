package racingcar;

public class Car {

    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public void move(int fuel) {
        // TODO racingcar.Constants.MOVE_CRITERIA = 4
        if (fuel >= 4) {
            ++position;
        }
    }
}
