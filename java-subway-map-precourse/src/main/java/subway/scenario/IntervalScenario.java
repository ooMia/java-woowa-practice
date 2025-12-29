package subway.scenario;

import subway.domain.IntervalRepository;
import subway.util.Console;

class IntervalScenario extends AbstractScenario {
    protected IntervalScenario(Console console) {
        super(console);
    }

    @Override
    public void run() {
        while (true) {
            outputView.printIntervalMenu();
            char menu = exceptionHandler.tryUntilValid(() -> inputView.readMenuFrom('1', '2', 'B'));
            if (menu == 'B') {
                break;
            }
            exceptionHandler.failsafe(() -> routeSubMenu(menu));
        }
    }

    private void routeSubMenu(char menu) {
        if (menu == '1') {
            register();
        }
        if (menu == '2') {
            delete();
        }
    }

    private void register() {
        String lineName = inputView.readStringWithMessage("## 노선을 입력하세요.");
        String stationName = inputView.readStringWithMessage("## 역이름을 입력하세요.");
        int oneBasedIndex = inputView.readIntWithMessage("## 순서를 입력하세요.");
        IntervalRepository.addInterval(lineName, stationName, oneBasedIndex);
        outputView.printInfo("구간이 등록되었습니다.");
    }

    private void delete() {
        String lineName = inputView.readStringWithMessage("## 삭제할 구간의 노선을 입력하세요.");
        String stationName = inputView.readStringWithMessage("## 삭제할 구간의 역을 입력하세요.");
        IntervalRepository.removeInterval(lineName, stationName);
        outputView.printInfo("구간이 삭제되었습니다.");
    }
}
