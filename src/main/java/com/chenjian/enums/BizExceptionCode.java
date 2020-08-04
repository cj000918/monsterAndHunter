package com.chenjian.enums;

import com.chenjian.exception.BaseBusinessCode;

public enum BizExceptionCode implements BaseBusinessCode {

    PARAMETER_MISSING("1001", "缺少必填参数"),
    ;

    private final String code;

    private final String msg;

    BizExceptionCode(String val, String info) {
        this.code = val;
        this.msg = info;

    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
