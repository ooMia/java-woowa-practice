package vendingmachine;

import java.util.List;

import vendingmachine.model.Coin;
import vendingmachine.model.Vendor;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.model.out.Balance;
import vendingmachine.model.out.CoinBalance;
import vendingmachine.service.MainService;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView out = new OutputView();
        MainService service = new MainService();

        Controller api = new Controller();
        try {
            // 이 문제는 Vendor라는 객체를 다뤄야하는 문제
            // 따라서, 인자로 Vendor가 넘어가야함
            // 특수한 방법으로 vendor를 다루는 방법은 커스텀 로직으로 처리

            Vendor vendor = new Vendor();
            VendorBalance vendorBalance = api.inputVendorBalance();
            List<Coin> coins = service.toRandomCoins(vendorBalance);
            vendor.addVendorBalance(coins);

            CoinBalance vendorCoinBalance = vendor.getVendorBalance();

            out.자판기가_보유한_동전(vendorCoinBalance);

            UserBalance userBalance = api.inputUserBalance();
            vendor.addUserBalance(userBalance);

            VendorItems vendorItems = api.inputVendorItems();
            vendor.addItems(vendorItems);

            while (service.canUserPerchaseAnything(vendor)) {
                Balance userBalanceLeft = vendor.getUserBalance();
                out.투입_금액(userBalanceLeft);

                Item userItem = api.inputUserItem(vendor);
                vendor.purchase(userItem);
            }

            CoinBalance userChangeCoinBalance = service.getUserChangeAsCoins(vendor);
            out.잔돈(userChangeCoinBalance);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
