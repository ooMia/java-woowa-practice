package racingcar;

import java.util.List;

import racingcar.model.in.CarName;
import racingcar.model.out.RacingStatus;
import racingcar.model.out.RacingWinners;
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
        System.out.println(in.CarNamesInstruction()); // #L96
        return ExceptionHandler.tryUntilValid(() -> {
            return in.inputCarNames();
        });
    }

    public int inputRaceTrial() {
        System.out.println(in.RaceTrialInstruction()); // #L98
        return ExceptionHandler.tryUntilValid(() -> {
            return in.inputRaceTrial().number();
        });
    }

    // --------- SERVICE ------------

    public void generateCars(int id, List<CarName> carNames) {
        service.initCars(id, carNames);
    }

    public RacingStatus runRaceOnce(int gameId) {
        var res = service.doRaceOnce(gameId);
        return new RacingStatus(res);
    }

    public RacingWinners getWinners(int gameId) {
        return service.currentWinners(gameId);
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
}
