package subway.scenario;

import java.util.List;
import subway.domain.IntervalRepository;
import subway.domain.LineRepository;
import subway.domain.StationRepository;
import subway.util.Console;

public class RootScenario extends AbstractScenario {

    private final StationScenario stationScenario;
    private final LineScenario lineScenario;
    private final IntervalScenario intervalScenario;

    protected RootScenario(Console console) {
        super(console);
        stationScenario = new StationScenario(console);
        lineScenario = new LineScenario(console);
        intervalScenario = new IntervalScenario(console);
    }

    private static void initialize(String lineName, List<String> stations) {
        stations.forEach(stationName -> {
            if (StationRepository.findStationByName(stationName).isEmpty()) {
                StationRepository.addStation(stationName);
            }
        });
        LineRepository.addLine(lineName, stations.getFirst(), stations.getLast());
        for (int i = 1; i < stations.size() - 1; ++i) {
            String stationName = stations.get(i);
            IntervalRepository.addInterval(lineName, stationName, i + 1);
        }
    }

    public static Runnable ofDefault(Console console) {
        initialize("2호선", List.of("교대역", "강남역", "역삼역"));
        initialize("3호선", List.of("교대역", "남부터미널역", "양재역", "매봉역"));
        initialize("신분당선", List.of("강남역", "양재역", "양재시민의숲역"));
        return new RootScenario(console);
    }

    @Override
    public void run() {
        while (true) {
            outputView.printRootMenu();
            char menu = exceptionHandler.tryUntilValid(() -> inputView.readMenuFrom('1', '2', '3', '4', 'Q'));
            if (menu == 'Q') {
                break;
            }
            exceptionHandler.failsafe(() -> routeSubMenu(menu));
        }
    }

    private void routeSubMenu(char menu) {
        if (menu == '1') {
            stationScenario.run();
        }
        if (menu == '2') {
            lineScenario.run();
        }
        if (menu == '3') {
            intervalScenario.run();
        }
        if (menu == '4') {
            outputView.printSubwayMap(IntervalRepository.getIntervals());
        }
    }
}
