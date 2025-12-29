package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.TestUtil;

class LineRepositoryTest {

    @BeforeEach
    void setUp() {
        LineRepository.addLine(new Line("2호선"));
        StationRepository.addStation(new Station("교대역"));
    }

    @AfterEach
    void tearDown() {
        TestUtil.resetRepositories();
    }

    @Test
    @DisplayName("지하철 노선의 목록을 조회할 수 있다.")
    void lines() {
        var lines = LineRepository.lines();
        var lineNames = lines.stream().map(Line::getName).toList();
        assertThat(lineNames).isEqualTo(List.of("2호선"));
    }

    @Test
    @DisplayName("지하철 노선을 등록할 수 있다. 노선 등록 시 상행 종점역과 하행 종점역을 입력받는다.")
    void addLine() {
        StationRepository.addStation(new Station("매봉역"));
        LineRepository.addLine("3호선", "교대역", "매봉역");

        var interval = IntervalRepository.getInterval("3호선");
        var stationNames = interval.getStations().stream().map(Station::getName);
        assertThat(stationNames).isEqualTo(List.of("교대역", "매봉역"));
    }


    @Test
    @DisplayName("지하철 노선을 삭제할 수 있다.")
    void deleteLine() {
        addLine();
        LineRepository.deleteLine("3호선");
        TestUtil.assertThatThrownBy(() -> LineRepository.findLineByName("3호선"));
    }

    @Test
    @DisplayName("중복된 지하철 노선 이름이 등록될 수 없다.")
    void duplicateNameThrows() {
        Assertions.assertDoesNotThrow(() -> LineRepository.addLine(new Line("호선")));
        TestUtil.assertThatThrownBy(() -> LineRepository.addLine(new Line("호선")));
    }

    @Test
    @DisplayName("지하철 노선 이름은 2글자 이상이어야 한다.")
    void nameLengthThrows() {
        TestUtil.assertThatThrownBy(() -> LineRepository.addLine(new Line("")));
        TestUtil.assertThatThrownBy(() -> LineRepository.addLine(new Line("선")));
        Assertions.assertDoesNotThrow(() -> LineRepository.addLine(new Line("호선")));
    }
}
