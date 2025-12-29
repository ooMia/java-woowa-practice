package subway.scenario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subway.domain.LineRepository;
import subway.domain.StationRepository;

class LineScenarioTest extends AbstractScenarioTest {

    protected LineScenarioTest() {
        super(LineScenario::new);
    }

    @BeforeEach
    void setUp() {
        StationRepository.addStation("교대역");
        StationRepository.addStation("역삼역");
    }

    @Test
    void register() {
        runWithInput("1", "2호선", "교대역", "역삼역").contains(
                "## 등록할 노선 이름을 입력하세요.",
                "## 등록할 노선의 상행 종점역 이름을 입력하세요.",
                "## 등록할 노선의 하행 종점역 이름을 입력하세요.",
                "[INFO] 지하철 노선이 등록되었습니다.");
    }

    @Test
    void delete() {
        LineRepository.addLine("2호선", "교대역", "역삼역");
        runWithInput("2", "2호선").contains("## 삭제할 노선 이름을 입력하세요.", "[INFO] 지하철 노선이 삭제되었습니다.");
    }

    @Test
    void query() {
        LineRepository.addLine("2호선", "교대역", "역삼역");
        runWithInput("3").contains("[INFO] 2호선");
    }
}
