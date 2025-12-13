package vendingmachine;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public class TestUtil {
    @SuppressWarnings("UnusedReturnValue")
    public static AbstractThrowableAssert<?, ? extends Throwable> assertThatThrownBy(ThrowingCallable callable) {
        return Assertions.assertThatThrownBy(callable).isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}
