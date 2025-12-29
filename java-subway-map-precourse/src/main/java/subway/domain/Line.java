package subway.domain;

import subway.util.GlobalExceptions;

// 노선 (Graph)
public class Line {
    private final String name;

    public Line(String name) {
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
        if (obj instanceof Line line) {
            return line.name.equals(name);
        }
        return super.equals(obj);
    }
}
