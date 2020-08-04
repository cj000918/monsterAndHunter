package com.chenjian.exception;

public class BusinessException extends BizException{

    public BusinessException(BaseBusinessCode businessCode){
        super(businessCode.getCode(), businessCode.getMsg());
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message){
        super(code, message);
    }
}
