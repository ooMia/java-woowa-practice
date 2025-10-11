package racingcar;

public class Car {

    // 프로그래밍 요구사항 - Car 객체

    // #L152
    // - 다음 Car 객체를 활용해 구현해야 한다.
    // - Car 기본 생성자를 추가할 수 없다.
    // - name, position 변수의 접근 제어자인 private을 변경할 수 없다.
    // - 가능하면 setPosition(int position) 메소드를 추가하지 않고 구현한다.

    // #L135
    // 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
    // 처음에 문제 읽을 때, 이동 가능하다고 잘못 읽었던 것이 실수
    // 일단 현재 구조에서 문제를 처리해보자

    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    // 추가 기능 구현

    // 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다. // #L44
    // 내부에서 직접 랜덤하게 움직일 수도 있지만, 불필요한 의존성이 생기고, 테스트도 불편해짐 
    public void forward(int fuel) {
        if (fuel < Constant.MIN_VALUE_FORWARD || Constant.MAX_VALUE_FORWARD < fuel)
            throw ErrorCode.CAR_INVALID_FUEL.exception();
        if (fuel >= Constant.STANDARD_VALUE_FORWARD) ++position;
    }

    // 사용하게 될 것 같아서 일단 정의해둠
    // TODO 필요없으면 삭제
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
