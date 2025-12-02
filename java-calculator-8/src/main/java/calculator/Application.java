package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String expression = Console.readLine();
        var result = new Calculator().add(expression);
        System.out.printf("결과 : %s", result);
    }
}
