package racingcar.car;

import java.util.function.IntSupplier;
import racingcar.car.strategy.DynamicMovingStrategy;
import racingcar.car.strategy.RacingCarWithDefaultStrategy;
import racingcar.car.strategy.RacingCarWithStrategy;

public final class CarFactory {

    private CarFactory() {
        /* no-op */
    }

    public static RacingCar ofRacingCar(String name) {
        var nameRule = new CarName(name);
        return new RacingCarImpl(nameRule.name());
    }

    public static RacingCarWithDefaultStrategy ofRacingCarWithDefaultStrategy(String name, IntSupplier supplier) {
        var racingCar = ofRacingCar(name);
        return new RacingCarWithDefaultStrategy() {
            @Override
            public void move() {
                int value = supplier.getAsInt();
                if (value >= 4) {
                    move(1);
                }
            }

            @Override
            public Status getStatus() {
                return racingCar.getStatus();
            }

            @Override
            public void move(int distance) {
                racingCar.move(distance);
            }
        };
    }

    /**
     * @deprecated 현재 동적인 전략 활용의 필요성이 없어 관리하지 않음
     */
    @Deprecated(since = "af8be4f5")
    @SuppressWarnings({"DeprecatedIsStillUsed", "java:S1133"})
    public static RacingCarWithStrategy<Integer> ofRacingCarWithStrategy(String name, IntSupplier supplier) {
        var racingCar = ofRacingCar(name);
        return new RacingCarWithStrategy<>() {
            @Override
            public void move(DynamicMovingStrategy<Integer> strategy) {
                int distance = strategy.distanceToMove(supplier.getAsInt());
                this.move(distance);
            }

            @Override
            public Status getStatus() {
                return racingCar.getStatus();
            }

            @Override
            public void move(int distance) {
                racingCar.move(distance);
            }
        };
    }
}
