package com.chenjian.entity.response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RestResponse<T>  {
    private static final long serialVersionUID = 5043120883324224451L;
    public static final String SUCC_CODE = "0";
    public static final String SUCC_MSG = "success";
    public static final String DEFAULT_ERR_CODE = "100000";
    public static final String DEFAULT_ERR_MSG = "fail";
    private String exceptCauseIp;
    private String exceptCauseApp;
    private String exceptClass;
    private String resultCode;
    private String resultMsg;
    private T data;

    public static final RestResponse<String> SUCCEED = new RestResponse("0", "success");
    public static final RestResponse<Void> VOID = new RestResponse((Object)null);
    public static final RestResponse<Boolean> TRUE;
    public static final RestResponse<Boolean> FALSE;

    public RestResponse() {
        this((T)null);
    }

    public RestResponse(T data) {
        this.resultCode = "0";
        this.resultMsg = "success";
        this.data = data;
    }

    public RestResponse(String resultCode, String resutMessage) {
        this.resultCode = resultCode;
        this.resultMsg = resutMessage;
    }

    public RestResponse(String resultCode, String resutMsg, T data) {
        this.resultCode = resultCode;
        this.resultMsg = resutMsg;
        this.data = data;
    }

    public static RestResponse<Long> createLong(Long value) {
        return new RestResponse(value);
    }

    public static RestResponse<Short> createShort(Short value) {
        return new RestResponse(value);
    }

    public static RestResponse<Integer> createInteger(Integer value) {
        return new RestResponse(value);
    }

    public static RestResponse<Float> createFloat(Float value) {
        return new RestResponse(value);
    }

    public static RestResponse<Double> createDouble(Double value) {
        return new RestResponse(value);
    }

    public static RestResponse<BigDecimal> createBigDecimal(BigDecimal value) {
        return new RestResponse(value);
    }

    public static RestResponse<Object> createObject(Object obj) {
        return new RestResponse(obj);
    }

    public String getExceptCauseIp() {
        return this.exceptCauseIp;
    }

    public void setExceptCauseIp(String exceptCauseIp) {
        this.exceptCauseIp = exceptCauseIp;
    }

    public String getExceptCauseApp() {
        return this.exceptCauseApp;
    }

    public void setExceptCauseApp(String exceptCauseApp) {
        this.exceptCauseApp = exceptCauseApp;
    }

    public String getExceptClass() {
        return this.exceptClass;
    }

    public void setExceptClass(String exceptClass) {
        this.exceptClass = exceptClass;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap();
        map.put("resultCode", this.resultCode);
        map.put("resultMsg", this.resultMsg);
        map.put("data", this.data);
        return map;
    }

    public String toString() {
        return "RestResponse [resultCode=" + this.resultCode + ", resultMsg=" + this.resultMsg + ", data=" + this.data + "]";
    }

    static {
        TRUE = new RestResponse("0", "success", Boolean.TRUE);
        FALSE = new RestResponse("0", "success", Boolean.FALSE);
    }
}
