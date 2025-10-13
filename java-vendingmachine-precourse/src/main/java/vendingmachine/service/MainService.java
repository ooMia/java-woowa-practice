package vendingmachine.service;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;
import vendingmachine.model.Vendor;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.out.CoinBalance;

public class MainService {
    private final Mapper mapper = new Mapper();
    private final Parser parser = new Parser();

    public <T> T parse(String line, Class<T> target) {
        return parser.parse(line, target);
    }

    public boolean canUserPerchaseAnything(Vendor vendor) {
        // 종료 조건
        // 1. 남은 금액이 상품의 최저 가격보다 적거나
        // 2. 모든 상품이 소진된 경우 바로 잔돈을 돌려준다.

        // 애매한 게 테스트가 필요할 것 같아서
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canUserPerchaseAnything'");
    }

    public CoinBalance getUserChangeAsCoins(Vendor vendor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserChangeAsCoins'");
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
