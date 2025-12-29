package subway.domain;

import subway.util.GlobalExceptions;

// 역 (Node)
@SuppressWarnings("ClassCanBeRecord")
public class Station {
    private final String name;

    public Station(String name) {
        this.name = name;
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(name.length() < 2);
    }

    public String getName() {
        return name;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Station station) {
            return station.name.equals(name);
        }
        return super.equals(obj);
    }
}
