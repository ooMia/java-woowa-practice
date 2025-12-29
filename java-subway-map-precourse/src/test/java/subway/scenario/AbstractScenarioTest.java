package subway.scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import subway.TestUtil;
import subway.util.Console;

abstract class AbstractScenarioTest {

    private final String menuExit;
    private final Function<Console, AbstractScenario> scenarioSupplier;

    private Queue<String> inputBuffer;
    private List<String> outputBuffer;
    private AbstractScenario scenario;

    protected AbstractScenarioTest(String menuExit, Function<Console, AbstractScenario> scenarioSupplier) {
        this.menuExit = menuExit;
        this.scenarioSupplier = scenarioSupplier;
    }

    @BeforeEach
    protected void initialize() {
        inputBuffer = new LinkedList<>();
        outputBuffer = new ArrayList<>();
        var console = new Console() {
            @Override
            public String readLine() throws EndOfBufferException {
                String input = inputBuffer.poll();
                if (Objects.isNull(input)) {
                    throw new EndOfBufferException();
                }
                return input;
            }

            @Override
            public void printLine(String message) {
                outputBuffer.add(message);
            }
        };
        scenario = scenarioSupplier.apply(console);
    }

    @AfterEach
    protected void tearDown() {
        TestUtil.resetRepositories();
    }

    protected ListAssert<String> runWithInput(String... input) {
        inputBuffer.addAll(Arrays.asList(input));
        inputBuffer.add(menuExit);
        try {
            scenario.run();
        } catch (EndOfBufferException ignored) {
            /* failsafe */
        }
        var concat = String.join("\n", outputBuffer);
        var parse = concat.split("\n");
        return Assertions.assertThat(Arrays.asList(parse));
    }

    private static class EndOfBufferException extends RuntimeException {
    }
}
