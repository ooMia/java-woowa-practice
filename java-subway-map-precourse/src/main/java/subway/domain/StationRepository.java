package subway.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import subway.util.GlobalExceptions;

public class StationRepository {
    private static final Set<Station> stations = new HashSet<>();

    private StationRepository() {
        /* no-op */
    }

    public static List<Station> stations() {
        return stations.stream().toList();
    }

    public static void addStation(Station station) {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!stations.add(station));
    }

    public static void addStation(String name) {
        stations.add(new Station(name));
    }

    public static boolean deleteStation(String name) {
        var station = new Station(name);
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(IntervalRepository.isRegistered(station));
        return stations.remove(station);
    }

    public static Optional<Station> findStationByName(String stationName) {
        try {
            return stations.stream().filter(station -> station.getName().equals(stationName)).findAny();
        } catch (NullPointerException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception(e);
        }
    }

    public static void clear() {
        stations.clear();
    }

}
