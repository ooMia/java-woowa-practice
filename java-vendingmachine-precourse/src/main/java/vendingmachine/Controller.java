package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.Vendor;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
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
        System.out.println(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public VendorItems inputVendorItems() {
        var target = VendorItems.class;
        System.out.println(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public UserBalance inputUserBalance() {
        var target = UserBalance.class;
        System.out.println(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public Item inputUserItem(Vendor vendor) {

        // 현재 최저 상품 가격이 500원이고
        // 잔여 금액은 1000원인데
        // 사용자가 2000원짜리 구매하려 하면 오류 발생
        // 즉, 입력부터 잘못된 것
        // 그래서 vendor 필요함

        var target = Item.class;
        System.out.println(in.instruction(target));
        return _tryParseUntilValid(target);
    }

}
