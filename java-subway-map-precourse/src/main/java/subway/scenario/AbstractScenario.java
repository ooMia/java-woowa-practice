package subway.scenario;

import subway.util.Console;
import subway.util.ExceptionUtils.ExceptionHandler;

@SuppressWarnings("unused")
abstract class AbstractScenario implements Runnable {

    protected final InputView inputView;
    protected final OutputView outputView;
    protected final ExceptionHandler exceptionHandler;

    protected AbstractScenario(Console console) {
        this.inputView = new InputView(console);
        this.outputView = new OutputView(console);
        this.exceptionHandler = new ExceptionHandler(console);
    }
}
