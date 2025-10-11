package racingcar.model.out;

import java.util.Map;

public record RacingStatus(Map<String, Integer> status) {

    // 결과를 보면 이렇게 나오니, 기본적으로 Map 형식
    // pobi : -
    // woni : 
    // jun : -

    // 하지만 Car 객체를 사용해야 한다면
    // List<Car>로 충분

    // 하지만 내부 모델 객체를 외부로 노출시키는 것은 별로 // NOTE#L40
    // 그러니까 그냥 Map으로 하자

    // 싶었는데 그렇게되면 생각보다 이 객체를 자주 만들어야 함
    // TODO 그건 나중에 생각하고 일단 진행 

}
