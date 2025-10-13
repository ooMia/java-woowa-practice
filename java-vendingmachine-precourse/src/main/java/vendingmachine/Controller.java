package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Vendor;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.model.out.Balance;
import vendingmachine.model.out.CoinBalance;
import vendingmachine.service.MainService;
import vendingmachine.util.ExceptionUtil;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

class Controller {

    private static final MainService serviceEntry = new MainService();
    private static final InputView in = new InputView();
    private static final OutputView out = new OutputView();

    private static <T> T _parse(Class<T> target) throws RuntimeException {
        return serviceEntry.parse(Console.readLine(), target);
    }

    private static <T> T _tryParseUntilValid(Class<T> target) {
        return ExceptionUtil.tryUntilValid(() -> _parse(target));
    }

    // read String from std.in return as model.in object

    // 42 사용자가 잘못된 값을 입력할 경우 ... 해당 부분부터 다시 입력을 받는다.
    public UserBalance inputUserBalance() {
        var target = UserBalance.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

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

    // TODO process model.in objects and return model.out object

    public CoinBalance getVendorBalance(Vendor vendor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVendorBalance'");
    }

    public Item inputUserItem(Vendor vendor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputUserItem'");
    }

    public boolean canPurchaseSomething(Vendor vendor) {
        // 종료 조건
        // 1. 남은 금액이 상품의 최저 가격보다 적거나
        // 2. 모든 상품이 소진된 경우 바로 잔돈을 돌려준다.

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canPurchase'");
    }

    public CoinBalance userExit(Vendor vendor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userExit'");
    }

    // print model.out object to std.out

    public void printCoinBalanceOfVendor(CoinBalance vendorCoinBalance) {
        System.out.println(out.stringify(vendorCoinBalance));
    }

    public void printBalanceOfUser(Balance balance) {
        System.out.println(out.stringify(balance));
    }

    public void printCoinBalanceOfUserChange(CoinBalance userChangeCoinBalance) {
        System.out.println(out.stringify(userChangeCoinBalance));
    }

}
