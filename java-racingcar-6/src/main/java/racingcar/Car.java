package racingcar;

public class Car {
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int MOVE_CRITERIA = 4;

    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
        validate();
    }

    private void validate() {
        if (name.length() > MAX_CAR_NAME_LENGTH)
            throw new IllegalArgumentException("자동차 이름 길이 제한을 초과했습니다.");
    }

    public void move(int fuel) {
        if (fuel >= MOVE_CRITERIA) ++position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
