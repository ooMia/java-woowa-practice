package vendingmachine.view;

import vendingmachine.model.out.SampleOutput;
import vendingmachine.util.ExceptionUtil;

public class OutputView {

    private static RuntimeException unsupported(Object obj) {
        return ExceptionUtil.unsupported("OutputView::stringify", obj.getClass().getSimpleName());
    }

    public <T> String stringify(Object target) {
        // L#127 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
        return switch (target) {
            case SampleOutput sampleOutput -> stringify(sampleOutput);
            default -> throw unsupported(target);
        };
    }

    // TODO 각 model.out 객체별로 stringify 메서드 추가

    String stringify(SampleOutput result) {
        return result.toString();
    }
}
