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
}
