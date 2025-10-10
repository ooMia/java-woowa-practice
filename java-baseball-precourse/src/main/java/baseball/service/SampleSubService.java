package baseball.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import baseball.Constant;
import camp.nextstep.edu.missionutils.Randoms;

class SampleSubService {
    private final Map<Integer, List<Integer>> map = new HashMap<>();

    String doWork(String s) {
        return s.toUpperCase();
    }

    List<Integer> get(int id) {
        return map.get(id);
    }

    void recreate(int key) {
        map.put(key, create());
    }

    List<Integer> create() {
        List<Integer> res = new ArrayList<>();
        while (res.size() < Constant.자리_수_제한) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!res.contains(randomNumber)) res.add(randomNumber);
        }
        return res;
    }
}
