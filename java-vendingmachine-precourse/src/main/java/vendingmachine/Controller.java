package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.model.out.SampleOutput;
import vendingmachine.service.MainService;
import vendingmachine.util.ExceptionUtil;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

class Controller {

    private static final MainService serviceEntry = new MainService();
    private static final InputView in = new InputView();
    private static final OutputView out = new OutputView();

    private static <T> T _parse(Class<T> target) throws RuntimeException {
        return serviceEntry.parse(Console.readLine(), target);
    }

    private static <T> T _tryParseUntilValid(Class<T> target) {
        return ExceptionUtil.tryUntilValid(() -> _parse(target));
    }

    // TODO read String from std.in return as model.in object

    public Foo inputFoo() {
        var target = Foo.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public Boo inputBoo() {
        var target = Boo.class;
        System.out.print(in.instruction(target));
        return _parse(target);
    }

    // TODO process model.in objects and return model.out object

    public SampleOutput process(Foo foo, Boo boo) {
        return serviceEntry.process(foo, boo);
    }

    // TODO print model.out object to std.out

    public void printSampleOutput(SampleOutput result) {
        System.out.println(out.stringify(result));
    }

}
