package vendingmachine.model;

import java.util.HashMap;
import java.util.Map;

import vendingmachine.ErrorCode;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.TradableItem;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.model.out.Balance;

public class Vendor {

    // 초기화: 내부 필드 초기화
    private final Map<TradableItem, Integer> items = new HashMap<>();
    private final Map<String, TradableItem> dict = new HashMap<>();
    private int vendorBalance = 0, userBalance = 0;

    //     반환되는 동전이 최소한이 되는 자판기를 구현한다.
    // L#30 반환되는 동전이 최소한? 이게 무슨 말이지?

    // - 자판기가 보유하고 있는 금액으로 동전을 무작위로 생성한다.
    //    - 투입 금액으로는 동전을 생성하지 않는다.
    // - 잔돈을 돌려줄 때 현재 보유한 최소 개수의 동전으로 잔돈을 돌려준다.
    // - 지폐를 잔돈으로 반환하는 경우는 없다고 가정한다.
    // - 상품명, 가격, 수량을 입력하여 상품을 추가할 수 있다.
    //    - 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
    // - 사용자가 투입한 금액으로 상품을 구매할 수 있다.
    // - 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우 바로 잔돈을 돌려준다.
    // - 잔돈을 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환한다.
    // 41 반환되지 않은 금액은 자판기에 남는다.

    public void addVendorBalance(VendorBalance vendorBalance) {
        this.vendorBalance += vendorBalance.money();
    }

    public void addUserBalance(UserBalance userBalance) {
        this.userBalance += userBalance.money();
    }

    public void addItems(VendorItems vendorItems) {
        for (var e : vendorItems.map().entrySet()) {
            var k = e.getKey();
            int prev = items.getOrDefault(k, 0);
            items.put(k, prev + e.getValue());
            dict.putIfAbsent(k.name(), k);
        }
    }

    public TradableItem purchase(Item userItem) {
        var item = dict.get(userItem.name()); // 구매하려는 아이템 추적
        var price = item.price(); // 가격 확인

        // 사용자 금액이 충분하지 않으면 예외 반환
        if (userBalance < price)
            throw ErrorCode.USER_NOT_ENOUGH_MONEY.exception();

        userBalance -= price; // 금액이 충분하면 사용자 보유액 감소
        items.put(item, items.get(item) - 1); // 아이템 보유수량 감소
        return item;
    }

    public Balance getUserBalance() {
        return new Balance(this.userBalance);
    }

    // TODO user exit 할 떄 vendorBalance에 대한 메서드 필요할듯

}
