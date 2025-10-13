package vendingmachine.service;

import vendingmachine.model.Vendor;
import vendingmachine.model.in.Boo;
import vendingmachine.model.in.Foo;
import vendingmachine.model.out.CoinBalance;
import vendingmachine.model.out.SampleOutput;

public class MainService {
    private final Mapper mapper = new Mapper();
    private final Parser parser = new Parser();

    public <T> T parse(String line, Class<T> target) {
        return parser.parse(line, target);
    }

    public SampleOutput process(Foo foo, Boo boo) {
        return mapper.map(foo, boo);
    }

    public CoinBalance getVendorBalanceAsCoins(Vendor vendor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVendorBalanceAsCoins'");
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

}
