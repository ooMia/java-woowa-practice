package racingcar.model.in;

import racingcar.ErrorCode;

public record CarName(String name) {
    // 자동차 이름 입력 String // #L42
    public CarName {
        // INPUT_자동차_이름_길이_제한("자동차 이름 길이는 5 이하여야 한다.", Constant.잘못된_입력_예외), 
        if (name.length() > 5)
            throw ErrorCode.INPUT_자동차_이름_길이_제한.exception();
    }
}
