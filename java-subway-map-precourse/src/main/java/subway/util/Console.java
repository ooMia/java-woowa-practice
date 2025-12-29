package subway.util;

/**
 * 표준 입출력 방식을 정의하는 간단한 인터페이스.
 */
public interface Console {
    String readLine();

    @SuppressWarnings("java:S106")
    default void printLine(String message) {
        System.out.println(message);
    }
}
