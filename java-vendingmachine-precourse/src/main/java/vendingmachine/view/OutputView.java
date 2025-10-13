package vendingmachine.view;

import vendingmachine.model.out.Balance;
import vendingmachine.model.out.CoinBalance;

public class OutputView {

    // 자판기가 보유한 동전
    public void 자판기가_보유한_동전(CoinBalance arg) {
        System.out.println(Message.OUT_HEADER_VENDOR_COIN_BALANCE);
        System.out.println(toCoinBalance(arg));
    }

    // 자판기가 보유한 동전
    public void 투입_금액(Balance arg) {
        System.out.print(Message.OUT_HEADER_USER_BALANCE);
        System.out.println(String.format("%d원", arg.money()));
    }

    // 자판기가 보유한 동전
    public void 잔돈(CoinBalance arg) {
        System.out.println(Message.OUT_HEADER_USER_CHANGE_COIN_BALANCE);
        System.out.println(toCoinBalance(arg));
    }

    // TODO CoinBalance 스펙 바꾸고 메서드 최적화
    private String toCoinBalance(CoinBalance arg) {
        var sb = new StringBuilder();
        if (arg.n500() > 0) sb.append(String.format("500원 - %d개", arg.n500())).append(System.lineSeparator());
        if (arg.n100() > 0) sb.append(String.format("100원 - %d개", arg.n100())).append(System.lineSeparator());
        if (arg.n50() > 0) sb.append(String.format("50원 - %d개", arg.n50())).append(System.lineSeparator());
        if (arg.n10() > 0) sb.append(String.format("10원 - %d개", arg.n10())).append(System.lineSeparator());
        return sb.toString();
    }
}
