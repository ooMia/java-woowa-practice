package racingcar.service;

import racingcar.model.in.SampleInput;
import racingcar.model.out.SampleOutput;

public class MainService {
    private final SampleSubService service = new SampleSubService();

    public SampleOutput process(SampleInput input) {
        var message = service.doWork("ok");
        return new SampleOutput(input.a(), message);
    }
}
