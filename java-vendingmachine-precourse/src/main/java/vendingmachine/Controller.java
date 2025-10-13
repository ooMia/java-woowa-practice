package vendingmachine;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Coin;
import vendingmachine.model.Vendor;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.model.out.CoinBalance;
import vendingmachine.service.MainService;
import vendingmachine.util.ExceptionUtil;
import vendingmachine.view.InputView;

class Controller {

    private static final MainService serviceEntry = new MainService();
    private static final InputView in = new InputView();

    private static <T> T _parse(Class<T> target) throws RuntimeException {
        return serviceEntry.parse(Console.readLine(), target);
    }

    private static <T> T _tryParseUntilValid(Class<T> target) {
        return ExceptionUtil.tryUntilValid(() -> _parse(target));
    }

    // read String from std.in return as model.in object

    // 42 사용자가 잘못된 값을 입력할 경우 ... 해당 부분부터 다시 입력을 받는다.
    public VendorBalance inputVendorBalance() {
        var target = VendorBalance.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public VendorItems inputVendorItems() {
        var target = VendorItems.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public UserBalance inputUserBalance() {
        var target = UserBalance.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public Item inputUserItem(Vendor vendor) {
        var target = Item.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    // process model.in objects and return model.out object

    public CoinBalance getVendorBalance(Vendor vendor) {
        return serviceEntry.getVendorBalanceAsCoins(vendor);
    }

    public boolean canPurchase(Vendor vendor) {
        return serviceEntry.canUserPerchaseAnything(vendor);
    }

    public CoinBalance userExit(Vendor vendor) {
        return serviceEntry.getUserChangeAsCoins(vendor);
    }

    public List<Coin> toRandomCoins(VendorBalance vendorBalance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toRandomCoins'");
    }

}
