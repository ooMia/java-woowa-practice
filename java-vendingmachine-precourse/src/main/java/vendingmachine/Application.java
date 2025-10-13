package vendingmachine;

import vendingmachine.model.Vendor;
import vendingmachine.model.in.Item;
import vendingmachine.model.in.UserBalance;
import vendingmachine.model.in.VendorBalance;
import vendingmachine.model.in.VendorItems;
import vendingmachine.model.out.Balance;
import vendingmachine.model.out.CoinBalance;

public class Application {
    public static void main(String[] args) {
        Controller api = new Controller();
        try {
            // 이 문제는 Vendor라는 객체를 다뤄야하는 문제
            // 따라서, 인자로 Vendor가 넘어가야함

            Vendor vendor = new Vendor();
            VendorBalance vendorBalance = api.inputVendorBalance();
            vendor.addVendorBalance(vendorBalance);

            // Coin이라는 클래스를 사용해서
            // 특수한 방법으로 vendor를 내가 다루는 방법은 controller 통해서 처리
            CoinBalance vendorCoinBalance = api.getVendorBalance(vendor);
            api.printCoinBalanceOfVendor(vendorCoinBalance);

            UserBalance userBalance = api.inputUserBalance();
            vendor.addUserBalance(userBalance);

            VendorItems vendorItems = api.inputVendorItems();
            vendor.addItems(vendorItems);

            // 그냥 구매하는 게 아니라
            // 직접 정의한 방식대로 내가 구매 방식을 통제하고 싶어서
            // 내가 vendor를 활용하는 방식이라 생각해서
            // controller 통해서 처리
            while (api.canPurchaseSomething(vendor)) {
                // 종료 조건
                // 1. 남은 금액이 상품의 최저 가격보다 적거나
                // 2. 모든 상품이 소진된 경우 바로 잔돈을 돌려준다.

                // 현재 최저 상품 가격이 500원이고
                // 잔여 금액은 1000원인데
                // 사용자가 2000원짜리 구매하려 하면 오류 발생
                // 즉, 입력부터 잘못된 것
                // 그래서 vendor 필요함
                Item userItem = api.inputUserItem(vendor);
                vendor.purchase(userItem);
                Balance userBalanceLeft = vendor.getUserBalance();
                api.printBalanceOfUser(userBalanceLeft);
            }

            CoinBalance userChangeCoinBalance = api.userExit(vendor);
            api.printCoinBalanceOfUserChange(userChangeCoinBalance);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
