package vendingmachine.view;

import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.util.ExceptionUtil;

public class InputView {

    private static RuntimeException unsupported(Class<?> cls) {
        return ExceptionUtil.unsupported("InputView::instruction", cls.getSimpleName());
    }

    // map instruction message for each model.in class

    public <T> String instruction(Class<T> target) {
        if (target == VendorBalance.class) return Message.IN_VENDOR_BALANCE.toString();
        if (target == VendorItems.class) return Message.IN_VENDOR_ITEMS.toString();
        if (target == UserBalance.class) return Message.IN_USER_BALANCE.toString();
        if (target == Item.class) return Message.IN_USER_ITEM.toString();
        throw unsupported(target);
    }

}
