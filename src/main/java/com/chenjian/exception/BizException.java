package com.chenjian.exception;

import com.chenjian.enums.ExceptionCode;

public class BizException extends BusinessRuntimeException{
    public BizException(String info) {
        super(ExceptionCode.FAIL.getCode(), info);
    }

    public BizException(String code, String info) {
        super(code, info);
    }
}
