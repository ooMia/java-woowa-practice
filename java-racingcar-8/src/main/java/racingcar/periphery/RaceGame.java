package racingcar.periphery;

import java.util.SequencedCollection;
import racingcar.car.Car.Status;
import racingcar.car.RacingCar;
import racingcar.car.strategy.RacingCarWithDefaultStrategy;

@SuppressWarnings("ClassCanBeRecord")
public final class RaceGame {

    private final SequencedCollection<RacingCarWithDefaultStrategy> cars;

    public RaceGame(SequencedCollection<RacingCarWithDefaultStrategy> cars) {
        this.cars = java.util.List.copyOf(cars);
    }

    public SequencedCollection<RacingCar.Status> iterate() {
        return cars.stream().map(car -> {
            car.move();
            return car.getStatus();
        }).toList();
    }

    public SequencedCollection<RacingCar.Status> getWinners() {
        var maxPosition = cars.stream()
                .map(RacingCar::getStatus)
                .mapToInt(Status::position)
                .max().orElseThrow();

        return cars.stream()
                .map(RacingCar::getStatus)
                .filter(status -> status.position() == maxPosition)
                .toList();
    }
}
