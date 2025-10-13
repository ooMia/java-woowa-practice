package vendingmachine.model.in;

import vendingmachine.Constant;
import vendingmachine.ErrorCode;

// 자판기가 초기에 보유하고 있는 금액
public record VendorBalance(int money) {
    public VendorBalance {
        // TODO 입력 받기 전 숫자 형식에 대한 검증 필요

        // ? 1원 단위도 가능할지

        if (money < Constant.MIN_BALANCE)
            throw ErrorCode.INPUT_BALANCE_UNDER_MIN.exception();
    }
}
