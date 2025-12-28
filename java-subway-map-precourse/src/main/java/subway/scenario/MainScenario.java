package subway.scenario;

import java.util.List;
import subway.domain.IntervalRepository;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.util.Console;

public class MainScenario extends AbstractScenario {

    protected MainScenario(Console console) {
        super(console);
        initialize("2호선", List.of("교대역", "강남역", "역삼역"));
        initialize("3호선", List.of("교대역", "남부터미널역", "양재역", "매봉역"));
        initialize("신분당선", List.of("강남역", "양재역", "양재시민의숲역"));
    }

    private static void initialize(String lineName, List<String> stations) {
        stations.stream().map(Station::new).forEach(StationRepository::addStation);
        LineRepository.addLine(lineName, stations.getFirst(), stations.getLast());
        for (int i = 1; i < stations.size() - 1; ++i) {
            String stationName = stations.get(i);
            IntervalRepository.addInterval(lineName, stationName, i + 1);
        }
    }

    public static Runnable of(Console console) {
        return new MainScenario(console);
    }

    @Override
    public void run() {

    }
}
