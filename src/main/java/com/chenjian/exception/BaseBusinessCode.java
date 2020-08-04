package com.chenjian.exception;

public interface BaseBusinessCode {
    String getCode();

    String getMsg();

    default BusinessException toException() {
        return new BusinessException(this.getCode(), this.getMsg());
    }
}
