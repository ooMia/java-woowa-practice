package subway.scenario;

import subway.dto.IntervalDto;
import subway.util.Console;

@SuppressWarnings({"ALL", "java:S1068"})
final class OutputView {

    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    void printInfo(String message) {
        console.printLine("[INFO] " + message);
    }

    void printRootMenu() {
        console.printLine("""
                ## 메인 화면
                1. 역 관리
                2. 노선 관리
                3. 구간 관리
                4. 지하철 노선도 출력
                Q. 종료
                """);
    }

    void printStationMenu() {
        console.printLine("""
                ## 역 관리 화면
                1. 역 등록
                2. 역 삭제
                3. 역 조회
                B. 돌아가기
                """);
    }

    void printLineMenu() {
        console.printLine("""
                ## 노선 관리 화면
                1. 노선 등록
                2. 노선 삭제
                3. 노선 조회
                B. 돌아가기
                """);
    }

    void printIntervalMenu() {
        console.printLine("""
                ## 구간 관리 화면
                1. 구간 등록
                2. 구간 삭제
                B. 돌아가기
                """);
    }

    void printEmptyLine() {
        console.printLine("");
    }

    void printInfoWithHeader(String header, Iterable<String> info) {
        console.printLine(header);
        info.forEach(this::printInfo);
    }

    void printSubwayMap(Iterable<IntervalDto> intervals) {
        console.printLine("## 지하철 노선도");
        intervals.forEach(dto -> {
            printInfo(dto.line());
            printInfo("---");
            dto.stations().forEach(this::printInfo);
        });
        printEmptyLine();
    }
}
