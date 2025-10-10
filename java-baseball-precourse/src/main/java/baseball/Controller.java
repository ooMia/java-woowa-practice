package baseball;

import baseball.model.in.Guess;
import baseball.model.in.SampleInput;
import baseball.model.out.GuessResult;
import baseball.model.out.SampleOutput;
import baseball.service.MainService;
import baseball.util.ExceptionHandler;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Controller {
    private final MainService service = new MainService();
    private final InputView in = new InputView();
    private final OutputView out = new OutputView();

    public SampleInput inputBoo() {
        return ExceptionHandler.tryUntilValid(() -> {
            in.guessInstruction();
            return in.readBoo();
        });
    }

    public SampleOutput process(SampleInput input) {
        return service.process(input);
    }

    public void outputBoo(SampleOutput output) {
        out.print(output);
    }

    public void printGreeting() {
        System.out.println(out.greeting());
    }

    public Guess inputGuess() {
        System.out.print(in.guessInstruction());
        return in.inputGuess();
    }

    public GuessResult judge(int id, Guess guess) {
        return service.judge(id, guess);
    }

    public void printGuessResult(GuessResult result) {
        System.out.println(out.getResult(result));
    }

    public int inputExitCode() {
        System.out.println(in.exitCodeInstruction());
        return in.inputExitCode().code();
    }

    public boolean isCorrect(GuessResult guessResult) {
        var res = service.isAnswer(guessResult);
        if (res) System.out.println(out.celebrate());
        return res;
    }

    public void reset(int id) {
        service.resetAnswer(id);
    }
}
