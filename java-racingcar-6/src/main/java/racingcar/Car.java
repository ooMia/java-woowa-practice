package racingcar;

public class Car {

    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
        validate();
    }

    private void validate(){
        if (name.length() > 5) { // TODO use racingcar.Constants.MAX_CAR_NAME_LENGTH = 5
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }

    public void move(int fuel) {
        // TODO racingcar.Constants.MOVE_CRITERIA = 4
        if (fuel >= 4) {
            ++position;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
