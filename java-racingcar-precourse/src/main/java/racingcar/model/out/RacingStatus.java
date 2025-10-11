package racingcar.model.out;

import java.util.List;

import racingcar.Car;

public record RacingStatus(List<Car> cars) {

    // 결과를 보면 이렇게 나오니, 기본적으로 Map 형식
    // pobi : -
    // woni : 
    // jun : -

    // 하지만 Car 객체를 사용해야 한다면
    // List<Car>로 충분

    // 내부 모델 객체를 외부로 노출시키는 것은 별로 // NOTE#L40
    // 하지만 이 문제의 제약을 생각하면 적극 활용하는 것이 가장 효율적

}
