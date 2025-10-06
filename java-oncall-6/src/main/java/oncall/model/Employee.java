package oncall.model;

import oncall.Constant;
import oncall.ErrorCode;

public record Employee(String name) {
    public Employee(String name) {
        this.name = name;
        if (name.length() > Constant.최대_닉네임_길이)
            throw ErrorCode.유효하지_않은_입력.exception();
    }
}
