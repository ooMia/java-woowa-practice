package racingcar;

import java.util.List;

import racingcar.model.in.CarName;
import racingcar.model.in.SampleInput;
import racingcar.model.out.RacingStatus;
import racingcar.model.out.RacingWinners;
import racingcar.model.out.SampleOutput;
import racingcar.service.MainService;
import racingcar.util.ExceptionHandler;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Controller {
    private final MainService service = new MainService();
    private final InputView in = new InputView();
    private final OutputView out = new OutputView();

    // --------- INPUT ------------

    // #L47
    // 사용자가 잘못된 값을 입력할 경우
    // `IllegalArgumentException`를 발생시키고,
    // "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.

    public List<CarName> inputCarNames() {
        return ExceptionHandler.tryUntilValid(() -> {
            System.out.print(in.CarNamesInstruction());
            return in.inputCarNames();
        });
    }

    public int inputRaceTrial() {
        return ExceptionHandler.tryUntilValid(() -> {
            System.out.print(in.CarNamesInstruction());
            return in.inputCarNames();
        });
    }

    // --------- SERVICE ------------

    public void generateCars(int id, Object carNames) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCars'");
    }

    public RacingStatus runRaceOnce(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runRaceOnce'");
    }

    public RacingWinners getWinners(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWinners'");
    }

    // --------- OUTPUT ------------

    public void printRacingHeader() {
        System.out.println(out.racingHeader());
    }

    public void printStatus(RacingStatus status) {
        System.out.println(out.status(status));
    }

    public void printWinners(RacingWinners winners) {
        System.out.println(out.winners(winners));
    }

    // TODO 나머지 삭제

    public SampleInput inputBoo() {
        return ExceptionHandler.tryUntilValid(() -> {
            in.inputBooHeader();
            return in.readBoo();
        });
    }

    public SampleOutput process(SampleInput input) {
        return service.process(input);
    }

    public void outputBoo(SampleOutput output) {
        out.print(output);
    }

}
