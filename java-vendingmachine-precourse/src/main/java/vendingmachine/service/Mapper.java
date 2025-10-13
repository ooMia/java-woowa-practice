package vendingmachine.service;

import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.model.out.SampleOutput;

// responsible for mapping models
// model.in -> (model) -> model.out
class Mapper {

    // TODO package-private helper methods

    SampleOutput map(Foo foo, Boo boo) {
        return new SampleOutput(boo.map());
    }

}
