package racingcar.model.in;

import racingcar.ErrorCode;

public record RaceTrial(int number) {
    // 시도 횟수 입력 int
    public RaceTrial {

        /**
         * TODO record 생성자로 String을 받아, int number 형태를 충족시킬 수 있는 방법 찾아보기
         * 1. static of 가능
         * A non-canonical constructor must invoke another constructor of the same classJava(16778976)
         */

        // TODO INPUT_횟수_수("시도 횟수는 숫자여야 한다.", Constant.잘못된_입력_예외), // #L90

        // 일단 지금은 형식에 대한 오류 책임은 호출자에게 전가
        // INPUT_횟수_자연수("시도 횟수는 자연수여야 한다.", Constant.잘못된_입력_예외),

        if (number < 0)
            throw ErrorCode.INPUT_횟수_자연수.exception();
    }
}
