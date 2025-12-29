package subway.scenario;

import subway.domain.Station;
import subway.domain.StationRepository;
import subway.util.Console;

class StationScenario extends AbstractScenario {

    protected StationScenario(Console console) {
        super(console);
    }

    private void routeSubMenu(char menu) {
        if (menu == '1') {
            register();
        }
        if (menu == '2') {
            delete();
        }
        if (menu == '3') {
            query();
        }
    }

    private void register() {
        String name = inputView.readStringWithMessage("## 등록할 역 이름을 입력하세요.");
        StationRepository.addStation(name);
        outputView.printInfo("지하철 역이 등록되었습니다.");
    }

    private void delete() {
        String name = inputView.readStringWithMessage("## 삭제할 역 이름을 입력하세요.");
        StationRepository.deleteStation(name);
        outputView.printInfo("지하철 역이 삭제되었습니다.");
    }

    private void query() {
        var info = StationRepository.stations().stream().map(Station::getName).toList();
        outputView.printInfoWithHeader("## 역 목록", info);
    }

    @Override
    public void run() {
        while (true) {
            outputView.printStationMenu();
            char menu = exceptionHandler.tryUntilValid(() -> inputView.readMenuFrom('1', '2', '3', 'B'));
            if (menu == 'B') {
                break;
            }
            exceptionHandler.failsafe(() -> routeSubMenu(menu));
        }
    }
}
