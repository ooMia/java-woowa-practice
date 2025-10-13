package vendingmachine.service;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;
import vendingmachine.model.in.VendorBalance;

public class MainService {
    private final Mapper mapper = new Mapper();
    private final Parser parser = new Parser();

    public <T> T parse(String line, Class<T> target) {
        return parser.parse(line, target);
    }

    public List<Coin> toRandomCoins(VendorBalance vendorBalance) {
        List<Coin> res = new ArrayList<>();
        // ApplicationTest#L23 정수형으로 모킹된 상태를 확인
        // 그래서 반드시 이런 식으로 호출해야 한다
        int amount = vendorBalance.money();
        while (amount > 0) {
            int v = Randoms.pickNumberInList(List.of(10, 50, 100, 500));
            if (v > amount) continue;
            amount -= v;
            res.add(Coin.of(v));
        }
        return res;
    }

}
