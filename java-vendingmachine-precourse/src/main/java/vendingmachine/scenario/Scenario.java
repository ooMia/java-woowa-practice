package vendingmachine.scenario;

import java.util.List;
import vendingmachine.Stock;
import vendingmachine.util.Console;

public class Scenario extends AbstractScenario {
    protected Scenario(Console console) {
        super(console);
    }

    public static Runnable ofDefault(){
        return new Scenario(camp.nextstep.edu.missionutils.Console::readLine);
    }

    @Override
    public void run() {
//        자판기가 보유하고 있는 금액을 입력해 주세요.
//        450
        int balance = inputView.readVendingMachineBalance();
        // 자판기가 보유한 금액은 무작위 함수를 사용해서 동전으로 변환해야 한다

//        자판기가 보유한 동전
//        500원 - 0개
//        100원 - 4개
//        50원 - 1개
//        10원 - 0개

List<Stock> stocks =         inputView.readVendingMachineStocks();
//        상품명과 가격, 수량을 입력해 주세요.
//[콜라,1500,20];[사이다,1000,10]


//        투입 금액을 입력해 주세요.
//        3000


//        투입 금액: 3000원
//        구매할 상품명을 입력해 주세요.
//        콜라


//        투입 금액: 1500원
//        구매할 상품명을 입력해 주세요.
//        사이다


//        투입 금액: 500원
//                잔돈
//        100원 - 4개
//        50원 - 1개
    }
}
