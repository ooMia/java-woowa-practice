package subway;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import subway.domain.IntervalRepository;
import subway.domain.LineRepository;
import subway.domain.StationRepository;

public class TestUtil {
    @SuppressWarnings("UnusedReturnValue")
    public static AbstractThrowableAssert<?, ? extends Throwable> assertThatThrownBy(ThrowingCallable callable) {
        return Assertions.assertThatThrownBy(callable).isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    public static void resetRepositories() {
        IntervalRepository.clear();
        LineRepository.clear();
        StationRepository.clear();
    }
}
