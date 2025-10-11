package racingcar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import racingcar.model.in.CarName;
import racingcar.model.out.RacingStatus;
import racingcar.model.out.RacingWinners;

public class MainService {
    private final GameStorageService service = new GameStorageService();

    public void initCars(int id, List<CarName> carNames) {
        service.createGame(id, carNames);
    }

    public RacingStatus doRaceOnce(int gameId) {
        service.raceOnce(gameId);
        var cars = service.currentStatus(gameId);
        Map<String, Integer> res = new HashMap<>();
        for (var car : cars)
            res.put(car.getName(), car.getPosition());
        return new RacingStatus(res);
    }

    public RacingWinners currentWinners(int gameId) {
        var winners = service.currentWinnersName(gameId);
        return new RacingWinners(winners);
    }
}
