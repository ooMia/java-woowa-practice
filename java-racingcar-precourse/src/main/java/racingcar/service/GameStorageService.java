package racingcar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import racingcar.Car;
import racingcar.model.in.CarName;

class GameStorageService {

    // gameId 별로 List<Car> 객체를 관리하자
    private final Map<Integer, List<Car>> storage = new HashMap<>();

    String doWork(String s) {
        return s.toUpperCase();
    }

    public void createGame(int id, List<CarName> carNames) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGame'");
    }

    public void raceOnce(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'raceOnce'");
    }

    public List<Car> currentStatus(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'currentStatus'");
    }

    public List<String> currentWinners(int gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'currentWinners'");
    }
}
