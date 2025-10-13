package vendingmachine.service;

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
        // if (target == Foo.class) return target.cast(toFoo(line));
        throw unsupported(target);
    }

    // TODO package-private helper methods (String -> model.in)

    // 입력 형식부터 보자
    // L#52 입력은 [콜라,1500,20];[사이다,1000,10] 처럼 상품 받는 거 밖에 없음
    // 아, 근데 명시는 안 해두었지만, 다음 내용들도 입력을 받음
    // L#82 자판기가 보유하고 있는 금액
    // L#94 투입 금액

    // 총 4개
    // 

}
