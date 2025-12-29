package subway.scenario;

import org.junit.jupiter.api.Test;

class StationScenarioTest extends AbstractScenarioTest {

    protected StationScenarioTest() {
        super(StationScenario::new);
    }

    @Test
    void register() {
        runWithInput("1", "잠실역").contains("## 등록할 역 이름을 입력하세요.", "[INFO] 지하철 역이 등록되었습니다.");
    }

    @Test
    void delete() {
        runWithInput("2", "잠실역").contains("## 삭제할 역 이름을 입력하세요.", "[INFO] 지하철 역이 삭제되었습니다.");
    }

    @Test
    void query() {
        runWithInput("1", "잠실역");
        runWithInput("3").contains("[INFO] 잠실역");
    }
}
