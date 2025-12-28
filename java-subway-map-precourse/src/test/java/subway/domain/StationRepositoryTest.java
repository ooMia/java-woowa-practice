package subway.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import subway.TestUtil;

class StationRepositoryTest {

    @SuppressWarnings("SameParameterValue")
    private void addStation(String stationName) {
        Assertions.assertDoesNotThrow(() -> StationRepository.addStation(new Station(stationName)));
    }

    @Test
    @DisplayName("지하철 역의 목록을 조회할 수 있다.")
    void stations() {
    }

    @Nested
    class deleteStationTest {

        @BeforeEach
        void setUp() {
            addStation("역삼역");
        }

        @AfterEach
        void tearDown() {
            RepositoryUtil.resetRepositories();
        }

        @SuppressWarnings("SameParameterValue")
        private boolean deleteStation(String stationName) {
            return Assertions.assertDoesNotThrow(() -> StationRepository.deleteStation(stationName));
        }

        @Test
        @DisplayName("존재하는 역을 삭제하면 true를 반환한다.")
        void notThrow() {
            Assertions.assertTrue(deleteStation("역삼역"));
        }

        @Test
        @DisplayName("존재하지 않는 역을 삭제하면 false를 반환한다.")
        void notExist_throws() {
            Assertions.assertFalse(deleteStation("강남역"));
        }

        @Test
        @DisplayName("노선에 등록된 역은 삭제할 수 없다.")
        void deleteStation_registered_throws() {
            LineRepository.addLine(new Line("2호선"));
            IntervalRepository.addInterval("2호선", "역삼역", 1);

            TestUtil.assertThatThrownBy(() -> StationRepository.deleteStation("역삼역"));
        }
    }

    @Nested
    class addStationTest {

        @AfterEach
        void tearDown() {
            RepositoryUtil.resetRepositories();
        }

        @Test
        @DisplayName("지하철 역 이름은 2글자 이상이어야 한다.")
        void nameLengthThrows() {
            TestUtil.assertThatThrownBy(() -> StationRepository.addStation(new Station("")));
            TestUtil.assertThatThrownBy(() -> StationRepository.addStation(new Station("역")));
            addStation("역역");
        }

        @Test
        @DisplayName("중복된 지하철 역 이름은 등록될 수 없다.")
        void addStation_duplicated_throws() {
            addStation("역역");

            TestUtil.assertThatThrownBy(() -> StationRepository.addStation(new Station("역역")));
        }
    }
}
