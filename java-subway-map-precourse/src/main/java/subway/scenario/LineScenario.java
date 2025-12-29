package subway.scenario;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.util.Console;
import subway.util.GlobalExceptions;

class LineScenario extends AbstractScenario {
    protected LineScenario(Console console) {
        super(console);
    }

    @Override
    public void run() {
        while (true) {
            outputView.printLineMenu();
            char menu = exceptionHandler.tryUntilValid(() -> inputView.readMenuFrom('1', '2', '3', 'B'));
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
        if (menu == '3') {
            query();
        }
    }

    private void register() {
        String lineName = inputView.readStringWithMessage("## 등록할 노선 이름을 입력하세요.");
        String stationFrom = inputView.readStringWithMessage("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
        String stationTo = inputView.readStringWithMessage("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
        LineRepository.addLine(lineName, stationFrom, stationTo);
        outputView.printInfo("지하철 노선이 등록되었습니다.");
    }

    private void delete() {
        String name = inputView.readStringWithMessage("## 삭제할 노선 이름을 입력하세요.");
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!LineRepository.deleteLine(name));
        outputView.printInfo("지하철 노선이 삭제되었습니다.");
    }

    private void query() {
        var info = LineRepository.lines().stream().map(Line::getName).toList();
        outputView.printInfoWithHeader("## 노선 목록", info);
    }
}
