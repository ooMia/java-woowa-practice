package vendingmachine.model.in;

import java.util.Map;

import vendingmachine.Constant;
import vendingmachine.ErrorCode;

// #L91 상품명과 가격, 수량
// 상품명과 가격이라는 것이 고정적이지 않고, 한 묶음 Item으로 취급하기 좋음
public record VendorItems(Map<TradableItem, Integer> map) {
    public VendorItems {
        for (TradableItem o : map.keySet()) {
            if (o.price() < Constant.MIN_PRICE)
                throw ErrorCode.INPUT_ITEM_PRICE_UNDER_MIN.exception();
            if (o.price() % Constant.UNIT_PRICE != 0)
                throw ErrorCode.INPUT_ITEM_PRICE_MOD_FAIL.exception();
        }
    }
}
