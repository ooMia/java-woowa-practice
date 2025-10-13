package vendingmachine.service;

import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.model.out.SampleOutput;

public class MainService {
    private final Mapper mapper = new Mapper();
    private final Parser parser = new Parser();

    public <T> T parse(String line, Class<T> target) {
        return parser.parse(line, target);
    }

    public SampleOutput process(Foo foo, Boo boo) {
        return mapper.map(foo, boo);
    }

}
