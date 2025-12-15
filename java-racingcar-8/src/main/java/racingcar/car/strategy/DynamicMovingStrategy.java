package racingcar.car.strategy;

/**
 * @deprecated 현재 동적인 전략 활용의 필요성이 없어 관리하지 않음
 */
@Deprecated(since = "af8be4f5")
@SuppressWarnings({"java:S1133", "unused"})
@FunctionalInterface
public interface DynamicMovingStrategy<T> {
    int distanceToMove(T value);
}
