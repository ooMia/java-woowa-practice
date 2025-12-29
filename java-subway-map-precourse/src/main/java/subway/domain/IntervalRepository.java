package subway.domain;

import java.util.LinkedHashMap;
import java.util.SequencedMap;
import subway.dto.IntervalDto;
import subway.util.GlobalExceptions;

public class IntervalRepository {
    private static final SequencedMap<Line, Interval> intervalMap = new LinkedHashMap<>();

    private IntervalRepository() {
        /* no-op */
    }

    public static void addInterval(String lineName, String stationName, int oneBasedIndex) {
        var line = LineRepository.findLineByName(lineName);
        intervalMap.putIfAbsent(line, new Interval());
        var interval = intervalMap.get(line);
        var station = StationRepository.findStationByName(stationName)
                .orElseThrow(GlobalExceptions.INVALID_ARGUMENTS::exception);
        interval.addStation(station, oneBasedIndex - 1);
    }

    public static void removeInterval(String lineName, String stationName) {
        var line = LineRepository.findLineByName(lineName);
        var interval = intervalMap.get(line);
        var station = StationRepository.findStationByName(stationName)
                .orElseThrow(GlobalExceptions.INVALID_ARGUMENTS::exception);
        interval.removeStation(station);
    }

    public static Interval getInterval(String lineName) {
        var line = LineRepository.findLineByName(lineName);
        return intervalMap.get(line);
    }

    public static void clear() {
        intervalMap.clear();
    }

    static boolean isRegistered(Station station) {
        return intervalMap.values().stream().map(Interval::getStations)
                .anyMatch(stations -> stations.contains(station));
    }

    static boolean removeLine(String lineName) {
        var line = new Line(lineName);
        return intervalMap.remove(line) != null;
    }

    public static Iterable<IntervalDto> getIntervals() {
        return intervalMap.entrySet().stream().map(e -> {
            var lineName = e.getKey().getName();
            var stationNames = e.getValue().getStations().stream().map(Station::getName).toList();
            return new IntervalDto(lineName, stationNames);
        }).toList();
    }
}
