package com.chenjian.entity.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

    public  Message(){
    }

    private static final long serialVersionUID = 1004L;

    private boolean success;

    private String msg;

    private int code;

    private String describe;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public static  Message fail(String context) {
        return fail(false, context, new ArrayList());
    }

    public static  Message fail(String context, List dataList) {
        return fail(false, context, dataList);
    }

    public static  Message fail(boolean success, String context, List dataList) {
        Message messageData = new Message();
        messageData.setMsg(context);
        messageData.setSuccess(success);
        messageData.setObject(dataList);
        return messageData;
    }

    public static  Message success(String context) {
        return success(context, new ArrayList());
    }

    public static  Message success(String context, List dataList) {
        Message messageData = new Message();
        messageData.setMsg(context);
        messageData.setSuccess(true);
        messageData.setObject(dataList);
        return messageData;
    }
}
