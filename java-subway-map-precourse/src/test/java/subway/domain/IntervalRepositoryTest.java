package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import subway.TestUtil;

class IntervalRepositoryTest {

    @SuppressWarnings("SameParameterValue")
    private void assertIntervalEquals(String lineName, List<String> expected) {
        var interval = IntervalRepository.getInterval(lineName);
        List<String> intervalNames = interval.getStations().stream().map(Station::getName).toList();
        assertThat(intervalNames).isEqualTo(expected);
    }

    @Nested
    class addIntervalTest {

        @BeforeEach
        void setUp() {
            LineRepository.addLine(new Line("2호선"));
            StationRepository.addStation(new Station("교대역"));
        }

        @AfterEach
        void tearDown() {
            RepositoryUtil.resetRepositories();
        }

        private void addInterval(String lineName, String stationName, int oneBasedIndex) {
            Assertions.assertDoesNotThrow(() -> IntervalRepository.addInterval(lineName, stationName, oneBasedIndex));
        }

        @Test
        @DisplayName("등록된 노선과 역에 대한 구간을 추가할 수 있다.")
        void registered_notThrows() {
            addInterval("2호선", "교대역", 1);
        }

        @Test
        @DisplayName("하나의 역은 여러개의 노선에 추가될 수 있다.")
        void multipleLine_notThrows() {
            LineRepository.addLine(new Line("3호선"));
            addInterval("2호선", "교대역", 1);
            addInterval("3호선", "교대역", 1);
        }

        @Test
        @DisplayName("구간 중간에 역을 추가할 수 있다.")
        void betweenStations_notThrows() {
            StationRepository.addStation(new Station("역삼역"));
            StationRepository.addStation(new Station("강남역"));

            addInterval("2호선", "교대역", 1);
            addInterval("2호선", "역삼역", 2);
            addInterval("2호선", "강남역", 2);

            assertIntervalEquals("2호선", List.of("교대역", "강남역", "역삼역"));
        }

        @Test
        @DisplayName("이미 등록된 노선과 역에 대한 구간은 추가할 수 없다.")
        void duplicated_throws() {
            Assertions.assertDoesNotThrow(() -> IntervalRepository.addInterval("2호선", "교대역", 1));
            TestUtil.assertThatThrownBy(() -> IntervalRepository.addInterval("2호선", "교대역", 1));
        }

        @Test
        @DisplayName("등록되지 않은 노선과 역에 대한 구간을 추가할 수 없다.")
        void notRegistered_throws() {
            TestUtil.assertThatThrownBy(() -> IntervalRepository.addInterval("9호선", "교대역", 1));
            TestUtil.assertThatThrownBy(() -> IntervalRepository.addInterval("2호선", "강남역", 1));
        }

        @Test
        @DisplayName("유효하지 않은 위치에 구간을 추가할 수 없다.")
        void invalidIndex_throws() {
            TestUtil.assertThatThrownBy(() -> IntervalRepository.addInterval("2호선", "교대역", 0));
            TestUtil.assertThatThrownBy(() -> IntervalRepository.addInterval("2호선", "교대역", 2));
        }
    }

    @Nested
    class removeIntervalTest {

        @BeforeEach
        void setUp() {
            LineRepository.addLine(new Line("2호선"));
            StationRepository.addStation(new Station("교대역"));
            StationRepository.addStation(new Station("강남역"));
            StationRepository.addStation(new Station("역삼역"));
        }

        @AfterEach
        void tearDown() {
            RepositoryUtil.resetRepositories();
        }

        @SuppressWarnings("SameParameterValue")
        private void removeInterval(String lineName, String stationName) {
            Assertions.assertDoesNotThrow(() -> IntervalRepository.removeInterval(lineName, stationName));
        }

        @Test
        @DisplayName("노선 중간에 등록된 역을 삭제할 수 있다.")
        void registered_notThrows() {
            IntervalRepository.addInterval("2호선", "교대역", 1);
            IntervalRepository.addInterval("2호선", "강남역", 2);
            IntervalRepository.addInterval("2호선", "역삼역", 3);

            removeInterval("2호선", "강남역");
            assertIntervalEquals("2호선", List.of("교대역", "역삼역"));
        }

        @Test
        @DisplayName("종점을 제거할 경우 다음 역이 종점이 된다.")
        void removeLast_notThrows() {
            IntervalRepository.addInterval("2호선", "교대역", 1);
            IntervalRepository.addInterval("2호선", "강남역", 2);
            IntervalRepository.addInterval("2호선", "역삼역", 3);

            removeInterval("2호선", "역삼역");
            assertIntervalEquals("2호선", List.of("교대역", "강남역"));
        }

        @Test
        @DisplayName("등록되지 않은 구간은 삭제할 수 없다.")
        void nonRegistered_throws() {
            IntervalRepository.addInterval("2호선", "교대역", 1);
            IntervalRepository.addInterval("2호선", "강남역", 2);
            IntervalRepository.addInterval("2호선", "역삼역", 3);

            TestUtil.assertThatThrownBy(() -> IntervalRepository.removeInterval("2호선", "사당역"));
        }

        @Test
        @DisplayName("노선에 포함된 역이 두 개 이하일 때는 역을 제거할 수 없다.")
        void sizeLimit_throws() {
            IntervalRepository.addInterval("2호선", "교대역", 1);
            IntervalRepository.addInterval("2호선", "강남역", 2);

            TestUtil.assertThatThrownBy(() -> IntervalRepository.removeInterval("2호선", "강남역"));
        }
    }
}
