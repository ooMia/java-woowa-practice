package racingcar.view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import racingcar.Constant;
import racingcar.ErrorCode;
import racingcar.model.in.CarName;
import racingcar.model.in.RaceTrial;

public class InputView {

    public String CarNamesInstruction() {
        return Message.IN_자동차_이름.text();
    }

    public List<CarName> inputCarNames() {
        var line = Console.readLine();
        // 이름 앞 뒤로 공백이 포함되어도 상관없으면, 공백 필터링도 빠지긴 해야함
        // 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다. // #L7 
        var names = line.split(Constant.자동차_이름_구분자_패턴);
        var res = new ArrayList<CarName>();
        for (var name : names)
            res.add(new CarName(name));
        return res;
    }

    public String RaceTrialInstruction() {
        return Message.IN_시도_횟수.text();
    }

    public RaceTrial inputRaceTrial() {
        var line = Console.readLine();
        int number;
        try {
            number = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw ErrorCode.INPUT_횟수_수.exception();
        }
        return new RaceTrial(number);
    }
}
