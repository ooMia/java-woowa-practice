package racingcar.service;

import java.util.List;

import racingcar.model.in.CarName;
import racingcar.model.in.SampleInput;
import racingcar.model.out.RacingStatus;
import racingcar.model.out.RacingWinners;
import racingcar.model.out.SampleOutput;

public class MainService {
    private final SampleSubService service = new SampleSubService();

    public SampleOutput process(SampleInput input) {
        var message = service.doWork("ok");
        return new SampleOutput(input.a(), message);
    }

    public void initCars(int id, List<CarName> carNames) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initCars'");
    }

    public RacingStatus doRaceOnce(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runRace'");
    }

    public RacingWinners currentWinners(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'currentWinners'");
    }
}
