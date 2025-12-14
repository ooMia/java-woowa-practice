package vendingmachine;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInListTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    @Disabled("통합 테스트 임시 비활성화")
    void 기능_테스트() {
        assertRandomNumberInListTest(() -> {
            run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
            assertThat(output()).contains("자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                    "투입 금액: 3000원", "투입 금액: 1500원");
        }, 100, 100, 100, 100, 50);
    }

    @Test
    @Disabled("통합 테스트 임시 비활성화")
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("-1");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    // 자판기가 설명할 수 있는 액수는 0원부터 990원까지 한정되어 있어. 그리고 그 방식도 한정되어 있지.
    //  - 10원 4개, 50원 1개, 100원 4개, 500원 1개가 각 동전의 최대 사용 개수야.
    // 상품의 가격이 만 원 이상이고, 사용자가 4321 전달했을 때, 자판기가 (500x1, 100x1, 50x1, 10x16)을 가지는 상황을 생각해보자
    //  1. 자판기가 반환해야 할 잔액은 321지, 990이 아니야
    //  2. 어떤 경우에도 반환될 잔액의 끝자리는 0이 될거야
    //  3. 자판기는 500원 하나만 남기고, 나머지 동전 모두 소모해서 310원을 반환하면 돼

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
