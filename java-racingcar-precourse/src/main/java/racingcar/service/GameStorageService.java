package racingcar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.Car;
import racingcar.model.in.CarName;

// 가능하면 package-private로 유지 // NOTE#L21
class GameStorageService {

    // gameId 별로 List<Car> 객체를 관리하자
    private final Map<Integer, List<Car>> storage = new HashMap<>();

    void createGame(int id, List<CarName> carNames) {
        var res = new ArrayList<Car>();
        for (var wrapped : carNames) {
            var name = wrapped.name();
            res.add(new Car(name));
        }
        storage.put(id, res);
    }

    void raceOnce(int gameId) {
        var cars = storage.get(gameId);
        for (var car : cars) {
            var fuel = Randoms.pickNumberInRange(0, 9); // #L183
            car.forward(fuel);
        }
    }

    List<Car> currentStatus(int gameId) {
        return storage.get(gameId);
    }

    List<String> currentWinnersName(int gameId) {
        var carsDesc = queryStatusByPositionDesc(gameId);

        // 현재 상태에서 Car 중에 가장 높은 position 가진 녀석들의 이름
        // 내림차순 정렬 후 탐색
        int maxPosition = carsDesc.get(0).getPosition();
        var res = new ArrayList<String>();
        for (var car : carsDesc) {
            var pos = car.getPosition();
            if (pos < maxPosition) break;
            res.add(car.getName());
        }
        return res;
    }

    private List<Car> queryStatusByPositionDesc(int gameId) {
        var cars = currentStatus(gameId);
        Collections.sort(cars, (o1, o2) -> Integer.compare(o2.getPosition(), o1.getPosition()));
        return cars;
    }

    // TODO 삭제
    String doWork(String s) {
        return s.toUpperCase();
    }
}
