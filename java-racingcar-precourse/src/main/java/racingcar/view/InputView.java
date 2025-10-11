package racingcar.view;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import racingcar.Constant;
import racingcar.model.in.CarName;
import racingcar.model.in.RaceTrial;
import racingcar.model.in.SampleInput;

public class InputView {
    public void inputBooHeader() {
        System.out.print(Message.IN_INSTRUCTION.text());
    }

    public SampleInput readBoo() {
        var number = Integer.parseInt(Console.readLine());
        return new SampleInput(number);
    }

    public String readLine() {
        return Console.readLine();
    }

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
        var number = Integer.parseInt(line);
        return new RaceTrial(number);
    }
}
