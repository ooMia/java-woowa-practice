package vendingmachine.view;

import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.util.ExceptionUtil;

public class InputView {

    private static RuntimeException unsupported(Class<?> cls) {
        return ExceptionUtil.unsupported("InputView::instruction", cls.getSimpleName());
    }

    // TODO map instruction message for each model.in class

    public <T> String instruction(Class<T> target) {
        if (target == Foo.class) return Message.IN_.toString();
        if (target == Boo.class) return "input boo: ";
        throw unsupported(target);
    }

}
