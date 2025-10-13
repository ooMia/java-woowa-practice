package vendingmachine;

import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.model.out.SampleOutput;

public class Application {
    public static void main(String[] args) {
        Controller api = new Controller();
        try {
            Foo foo = api.inputFoo();
            Boo boo = api.inputBoo();
            SampleOutput result = api.process(foo, boo);
            api.printSampleOutput(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
