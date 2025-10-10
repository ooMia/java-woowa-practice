package baseball;

import baseball.util.ExceptionHandler;

public final class Constant {
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionHandler.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }

    // 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임 // #L39
    public static final int 자리_수_제한 = 3;
    public static final int 각_자리_최솟값 = 1, 각_자리_최댓값 = 9;

    // 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다. // #L50
    public static final Class<IllegalArgumentException> 예외_클래스_잘못된_입력 = IllegalArgumentException.class;

    // 컴퓨터의 정답은 1에서 9까지 서로 다른 임의의 수 3개이다. // #L46
    // 컴퓨터뿐만 아니라 사용자의 입력에도 `서로 다른 3자리 수`라는 제약이 있음 // #L56

    // 정리하면
    // 1. 입력 문자열의 길이 == 3
    // 2. 각 문자(char)를 정수화하고, 고유한 수의 개수도 3이어야 함

    public static final int 기호_게임_재시작 = 1, 기호_게임_종료 = 2; // #L57

    // 볼, 스트라이크, 낫싱 등의 표기 // #L64-76
    // 이외에 다양한 출력 사양 // #L59-106
    // view.Message에서 별도로 정리

}
