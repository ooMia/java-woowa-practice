package subway.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import subway.util.GlobalExceptions;
import subway.util.ValueRules;
import subway.util.ValueRules.InvalidValueException;

public class Interval {
    private final List<Station> stations = new LinkedList<>();

    public void addStation(Station station, int index) {
        validate(index);
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(stations.stream().anyMatch(station::equals));
        if (index == stations.size()) {
            stations.add(station);
            return;
        }
        stations.add(index, station);
    }

    private void validate(int index) {
        try {
            ValueRules.ofInRangeInclusive(0, stations.size()).validate(index);
        } catch (InvalidValueException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception(e);
        }
    }

    public List<Station> getStations() {
        return Collections.unmodifiableList(stations);
    }

    void removeStation(Station station) {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(stations.size() <= 2);
        stations.remove(station);
    }
}
