package racingcar;

import racingcar.model.in.SampleInput;
import racingcar.model.out.SampleOutput;
import racingcar.service.MainService;
import racingcar.util.ExceptionHandler;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Controller {
    private final MainService mainService = new MainService();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public SampleInput inputBoo() {
        return ExceptionHandler.tryUntilValid(() -> {
            inputView.inputBooHeader();
            return inputView.readBoo();
        });
    }

    public SampleOutput process(SampleInput input) {
        return mainService.process(input);
    }

    public void outputBoo(SampleOutput output) {
        outputView.print(output);
    }

    public Object inputCarNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputCarNames'");
    }

    public int inputRaceTrial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputRaceTrialNumber'");
    }

    public void generateCars(int id, Object carNames) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCars'");
    }

    public Object runRaceOnce(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runRaceOnce'");
    }

    public void printResult(Object result) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printResult'");
    }

    public Object getWinners(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWinners'");
    }

    public void printWinners(Object winners) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printWinners'");
    }

    // #L47
    // 사용자가 잘못된 값을 입력할 경우
    // `IllegalArgumentException`를 발생시키고,
    // "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
}
