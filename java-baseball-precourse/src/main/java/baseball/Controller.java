package baseball;

import baseball.model.in.SampleInput;
import baseball.model.out.SampleOutput;
import baseball.service.MainService;
import baseball.util.ExceptionHandler;
import baseball.view.InputView;
import baseball.view.OutputView;

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
