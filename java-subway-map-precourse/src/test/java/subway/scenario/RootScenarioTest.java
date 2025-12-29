package subway.scenario;

import org.junit.jupiter.api.Test;

class RootScenarioTest extends AbstractScenarioTest {

    protected RootScenarioTest() {
        super("Q", console -> (AbstractScenario) RootScenario.ofDefault(console));
    }

    @Test
    void stationMenu() {
        runWithInput("1", "3", "B").contains(
                "## 역 관리 화면",
                "## 역 목록",
                "[INFO] 교대역",
                "[INFO] 강남역",
                "[INFO] 역삼역",
                "[INFO] 남부터미널역",
                "[INFO] 양재역",
                "[INFO] 양재시민의숲역",
                "[INFO] 매봉역");
    }

    @Test
    void lineMenu() {
        runWithInput("2", "3", "B").contains(
                "## 노선 관리 화면",
                "## 노선 목록",
                "[INFO] 2호선",
                "[INFO] 3호선",
                "[INFO] 신분당선");
    }

    @Test
    void intervalMenu() {
        runWithInput("3", "B").contains("## 구간 관리 화면");
    }


    @Test
    void subwayMapMenu() {
        runWithInput("4").containsSubsequence(
                "## 지하철 노선도",
                "[INFO] 2호선",
                "[INFO] ---",
                "[INFO] 교대역",
                "[INFO] 강남역",
                "[INFO] 역삼역",
                "[INFO] 3호선",
                "[INFO] ---",
                "[INFO] 교대역",
                "[INFO] 남부터미널역",
                "[INFO] 양재역",
                "[INFO] 매봉역",
                "[INFO] 신분당선",
                "[INFO] ---",
                "[INFO] 강남역",
                "[INFO] 양재역",
                "[INFO] 양재시민의숲역"
        );

    }
}
