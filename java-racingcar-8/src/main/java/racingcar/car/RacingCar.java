package racingcar.car;

public interface RacingCar extends Car {
    @Override
    RacingCar.Status getStatus();

    interface Status extends Car.Status, Comparable<RacingCar.Status> {
    }
}
