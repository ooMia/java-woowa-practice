package racingcar.service;

import java.util.List;

import racingcar.Car;
import racingcar.model.in.CarName;
import racingcar.model.out.RacingWinners;

public class MainService {
    private final GameStorageService service = new GameStorageService();

    public void initCars(int id, List<CarName> carNames) {
        service.createGame(id, carNames);
    }

    public List<Car> doRaceOnce(int gameId) {
        service.raceOnce(gameId);
        return service.currentStatus(gameId);
    }

    public RacingWinners currentWinners(int gameId) {
        var winners = service.currentWinnersName(gameId);
        return new RacingWinners(winners);
    }
}
