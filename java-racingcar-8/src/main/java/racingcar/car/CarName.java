package racingcar.car;

import java.util.regex.Pattern;

record CarName(String name) {
    static final Pattern PATTERN_DEFAULT = Pattern.compile("[a-z]+");

    CarName {
        if (name == null || name.isBlank() || name.length() > 5) {
            throw new IllegalArgumentException();
        }

        if (!PATTERN_DEFAULT.matcher(name).matches()) {
            throw new IllegalArgumentException();
        }
    }
}
