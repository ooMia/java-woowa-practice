package vendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import vendingmachine.model.TradableItem;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.util.ExceptionUtil;

// responsible for parsing inputs
// String -> model.in
class Parser {

    private static RuntimeException unsupported(Class<?> cls) {
        return ExceptionUtil.unsupported("Parser::parse", cls.getSimpleName());
    }

    private static String[] _split(String delimiter, String line) {
        return line.split(String.format("\\s*%s\\s*", delimiter));
    }

    public <T> T parse(String line, Class<T> target) {
        if (target == VendorBalance.class) return target.cast(toVendorBalance(line));
        if (target == VendorItems.class) return target.cast(toVendorItems(line));
        if (target == UserBalance.class) return target.cast(toUserBalance(line));
        if (target == Item.class) return target.cast(toItem(line));
        throw unsupported(target);
    }

    // TODO package-private helper methods (String -> model.in)

    // L#82 자판기 보유 금액
    // 450
    VendorBalance toVendorBalance(String line) {
        int res = Integer.parseInt(line);
        return new VendorBalance(res);
    }

    // L#52 자판기 보유 상품
    // [콜라,1500,20];[사이다,1000,10]
    VendorItems toVendorItems(String line) {
        Map<TradableItem, Integer> res = new HashMap<>();
        // 1. split ;
        var _items = _split(";", line);

        // for-each
        for (var _item : _items) {
            var item = _item.substring(1, _item.length()); // 2. subString [1, N)
            var itemArgs = _split(",", item); // 3. split ,
            // String, int, int
            var key = new TradableItem(itemArgs[0], Integer.parseInt(itemArgs[1]));
            var value = Integer.parseInt(itemArgs[2]);
            res.put(key, value);
        }
        return new VendorItems(res);
    }

    // L#94 투입 금액
    // 3000
    UserBalance toUserBalance(String line) {
        int res = Integer.parseInt(line);
        return new UserBalance(res);
    }

    // L#99 구매 아이템
    // 콜라
    Item toItem(String line) {
        return new Item(line.trim());
    }

}
