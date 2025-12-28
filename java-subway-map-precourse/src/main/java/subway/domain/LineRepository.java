package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import subway.util.GlobalExceptions;

@SuppressWarnings("java:S1118")
public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    static void addLine(Line line) {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(lines.stream().anyMatch(line::equals));
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        var res1 = lines.removeIf(line -> Objects.equals(line.getName(), name));
        var res2 = IntervalRepository.removeLine(name);
        return res1 && res2;
    }

    static Line findLineByName(String lineName) {
        try {
            return lines.stream().filter(line -> line.getName().equals(lineName)).findAny().orElseThrow();
        } catch (NullPointerException | NoSuchElementException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception(e);
        }
    }

    static void clear() {
        lines.clear();
    }

    public static void addLine(String lineName, String stationFrom, String stationTo) {
        var line = new Line(lineName);
        addLine(line);
        StationRepository.findStationByName(stationFrom).orElseThrow(GlobalExceptions.INVALID_ARGUMENTS::exception);
        StationRepository.findStationByName(stationTo).orElseThrow(GlobalExceptions.INVALID_ARGUMENTS::exception);
        IntervalRepository.addInterval(lineName, stationFrom, 1);
        IntervalRepository.addInterval(lineName, stationTo, 2);
    }
}
