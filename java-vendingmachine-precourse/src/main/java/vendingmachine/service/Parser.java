package vendingmachine.service;

import java.util.Map;

import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.util.ExceptionUtil;

// responsible for parsing inputs
// String -> model.in
class Parser {

    private static RuntimeException unsupported(Class<?> cls) {
        return ExceptionUtil.unsupported("Parser::parse", cls.getSimpleName());
    }

    private static String[] _split(String delimiter, String line) {
        return line.split(String.format("\\s*%s\\s*", delimiter));
    }

    public <T> T parse(String line, Class<T> target) {
        if (target == Foo.class) return target.cast(toFoo(line));
        if (target == Boo.class) return target.cast(toBoo(line));
        throw unsupported(target);
    }

    // TODO package-private helper methods (String -> model.in)

    Foo toFoo(String line) {
        return new Foo(Integer.parseInt(line));
    }

    Boo toBoo(String line) {
        return new Boo(Map.of(
                "a", 1,
                "b", 2
        ));
    }
}
