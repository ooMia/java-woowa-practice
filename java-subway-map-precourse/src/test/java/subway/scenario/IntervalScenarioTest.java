package subway.scenario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subway.domain.IntervalRepository;
import subway.domain.LineRepository;
import subway.domain.StationRepository;

class IntervalScenarioTest extends AbstractScenarioTest {

    protected IntervalScenarioTest() {
        super(IntervalScenario::new);
    }

    @BeforeEach
    void setUp() {
        StationRepository.addStation("교대역");
        StationRepository.addStation("강남역");
        StationRepository.addStation("역삼역");
        LineRepository.addLine("2호선", "교대역", "역삼역");
    }

    @Test
    void register() {
        runWithInput("1", "2호선", "강남역", "2").contains(
                "## 노선을 입력하세요.",
                "## 역이름을 입력하세요.",
                "## 순서를 입력하세요.",
                "[INFO] 구간이 등록되었습니다.");
    }

    @Test
    void delete() {
        IntervalRepository.addInterval("2호선", "강남역", 2);

        runWithInput("2", "2호선", "역삼역").contains(
                "## 삭제할 구간의 노선을 입력하세요.",
                "## 삭제할 구간의 역을 입력하세요.",
                "[INFO] 구간이 삭제되었습니다.");
    }
}
