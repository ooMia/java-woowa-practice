package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP);
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("pobi,javaji");
                    assertThat(output()).contains(ERROR_MESSAGE);
                });
    }

    @Test
    void 예제_1() {
        // 2 4 3
        // o o o // MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        // o o o // MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        // x o o // STOP, MOVING_FORWARD, MOVING_FORWARD
        // x o x // STOP, MOVING_FORWARD, STOP
        // x x x // STOP, STOP, STOP
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "5");
                    assertThat(output()).contains(
                            "pobi : --",
                            "woni : ----",
                            "jun : ---",
                            "최종 우승자 : woni");
                },
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                STOP, MOVING_FORWARD, MOVING_FORWARD,
                STOP, MOVING_FORWARD, STOP,
                STOP, STOP, STOP);
    }

    @Test
    void 예제_2() {
        // 5 4 5
        // o o o // MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        // o o o // MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        // o o o // MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        // o o o // MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        // o x o // MOVING_FORWARD, STOP, MOVING_FORWARD
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "5");
                    assertThat(output()).contains(
                            "pobi : --",
                            "woni : ----",
                            "jun : ----",
                            "최종 우승자 : pobi, jun");
                },
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, STOP, MOVING_FORWARD);
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
