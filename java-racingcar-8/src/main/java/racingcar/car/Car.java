package racingcar.car;

public interface Car {
    void move(int distance);

    Status getStatus();

    interface Status {
        String name();

        int position();
    }
}
