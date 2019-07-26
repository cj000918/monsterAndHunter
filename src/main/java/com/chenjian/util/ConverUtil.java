package com.chenjian.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName ConverUtil
 * @Description
 * @Author chenjian
 * @Date 2019/2/27 18:55
 **/
public class ConverUtil {


    public static final String BIRTHDAY_DATE_FORMAT="yyyy-MM-dd";

    public static final boolean DEFAULT_BOOLEAN_VALUE = false;

    public static final String DEFAULT_BOOLEAN_STRING_FALSE_VALUE = "false";

    public static final String DEFAULT_BOOLEAN_INT_FALSE_VALUE = "0";

    public static final int DEFAULT_INT_VALUE = 0;

    public static final long DEFAULT_LONG_VALUE = 0L;

    public static final float DEFAULT_FLOAT_VALUE = 0.0F;

    public static final double DEFAULT_DOUBLE_VALUE = 0.0D;

    public static final String DEFAULT_STRING_VALUE = null;

    public static int convertStringToInt(String intStr) {
        return convertStringToInt(intStr, DEFAULT_INT_VALUE);
    }

    public static int convertStringToInt(String intStr,int exceptionResult){
        int result=exceptionResult;
        if(intStr==null || "".equals(intStr.trim())) return exceptionResult;
        try {
            result=Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            return exceptionResult;
        }
        return result;
    }

    public static long convertStringToLong(String intStr){
        return convertStringToLong(intStr, DEFAULT_LONG_VALUE);
    }

    public static long convertStringToLong(String intStr,long exceptionResult){
        long result=exceptionResult;
        if(intStr==null || "".equals(intStr.trim())) return exceptionResult;
        try {
            result=Long.parseLong(intStr);
        } catch (NumberFormatException e) {
        }
        return result;
    }

    public static boolean convertStringToBoolean(String str){
        return convertStringToBoolean(str, DEFAULT_BOOLEAN_VALUE);
    }

    public static boolean convertStringToBoolean(String str,boolean exceptionResult){
        boolean result=false;
        if(str==null || "".equals(str.trim())) return false;
        try {
            result=Boolean.parseBoolean(str);
        } catch (Exception e) {
        }
        return result;
    }

    public static double convertStringToDouble(String str){

        return convertStringToDouble(str, DEFAULT_DOUBLE_VALUE);
    }

    public static double convertStringToDouble(String str,double exceptionResult){
        double result=exceptionResult;
        if(str==null || "".equals(str.trim())) return exceptionResult;
        try {
            result=Double.parseDouble(str);
        }catch(Exception e){

        }
        return result;
    }


    public static double keepScaleOfDouble(double value){
        BigDecimal bg = new BigDecimal(Double.toString(value)); //转成string类型，可以避免转换为bigdecimal时候丢失精度
        double resValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        return resValue;
    }

    /**
     * double数四舍五入保留scale小数位
     * @param value
     * @param scale
     * @return double
     * @author wxm
     */
    public static double keepScaleOfDouble(double value, int scale){
        BigDecimal bg = new BigDecimal(Double.toString(value)); //转成string类型，可以避免转换为bigdecimal时候丢失精度
        double resValue = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();

        return resValue;
    }

    /**
     * double值以ROUND_UP舍入模式保留scale位小数
     * @param value
     * @param scale
     * @return
     */
    public static double keepRoundUpScaleOfDouble(double value, int scale){
        BigDecimal bg = new BigDecimal(Double.toString(value));
        double resValue = bg.setScale(scale, BigDecimal.ROUND_UP).doubleValue();
        return resValue;
    }

    /**
     * double值以ROUND_DOWN舍入模式保留scale位小数
     * @param value
     * @param scale
     * @return
     */
    public static double keepRoundDownScaleOfDouble(double value, int scale){
        BigDecimal bg = new BigDecimal(Double.toString(value));
        double resValue = bg.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
        return resValue;
    }

    /**
     * 保留scale位小数的除法运算
     * @param dividend 分子/被除数
     * @param divisor 分母/除数
     * @param scale 四四舍五入,保留scale位小数
     * @param exceptionValue 异常返回值
     * @return
     */
    public static double keepScaleOfDivisionOperation(double dividend, double divisor, int scale, double exceptionValue){
        double result = 0;
        try {
            BigDecimal b1 = new BigDecimal(Double.toString(dividend));
            BigDecimal b2 = new BigDecimal(Double.toString(divisor));
            result = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            result = exceptionValue;
        }
        return result;
    }

    public static int convertObjectToInt(Object intObj){
        return convertObjectToInt(intObj, DEFAULT_INT_VALUE);
    }
    public static int convertObjectToInt(Object intObj,int exceptionResult){
        int result=exceptionResult;
        String intStr = intObj==null?null:intObj.toString();
        if(intStr==null || "".equals(intStr.trim())) return exceptionResult;
        try {
            result=Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            return exceptionResult;
        }
        return result;
    }

    public static long convertObjectToLong(Object longObj){
        return convertObjectToLong(longObj,DEFAULT_LONG_VALUE);
    }
    public static long convertObjectToLong(Object longObj,long exceptionResult){
        long result=exceptionResult;
        String longStr = longObj==null?null:longObj.toString();
        if(longStr==null || "".equals(longStr.trim())) return exceptionResult;
        try {
            result=Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        return result;
    }

    public static boolean convertObjectToBoolean(Object booleanObj){
        return convertObjectToBoolean(booleanObj, DEFAULT_BOOLEAN_VALUE);
    }

    /**
     *
     * @Title: convertObjectToBoolean
     * @Description: booleanObj  值false或字符串"false"时返回false，值true或字符串"true"返回为true
     * @param: @param booleanObj
     * @param: @param exceptionResult
     * @param: @return
     * @return: boolean
     * @throws:
     */
    public static boolean convertObjectToBoolean(Object booleanObj,boolean exceptionResult){
        boolean result=exceptionResult;
        String booleanStr = booleanObj==null?null:booleanObj.toString();
        if(booleanStr==null || "".equals(booleanStr.trim())) return exceptionResult;
        try {
            result=Boolean.parseBoolean(booleanStr);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    /**
     *
     * @Title: convertToBoolean
     * @Description:booleanObj 为0、false、null、或者空字符串时返回false，其他返回为true
     * @param: @param booleanObj
     * @param: @param exceptionResult
     * @param: @return
     * @return: boolean
     * @throws:
     */
    public static boolean convertToBoolean(Object booleanObj){

        String booleanStr = booleanObj==null?null:booleanObj.toString();
        if(booleanStr==null || "".equals(booleanStr.trim())) return false;

        if(DEFAULT_BOOLEAN_INT_FALSE_VALUE.equalsIgnoreCase(booleanStr) || DEFAULT_BOOLEAN_STRING_FALSE_VALUE.equalsIgnoreCase(booleanStr)) {
            return false;
        }
        return true;
    }

    public static double convertObjectToDouble(Object doubleObj){
        return convertObjectToDouble(doubleObj, DEFAULT_DOUBLE_VALUE);
    }

    public static double convertObjectToDouble(Object doubleObj,double exceptionResult){
        double result=exceptionResult;
        String doubleStr = doubleObj==null?null:doubleObj.toString();
        if(doubleStr==null || "".equals(doubleStr.trim())) return exceptionResult;
        try {
            result=Double.parseDouble(doubleStr);
        }catch(Exception e){

        }
        return result;
    }

    public static float convertObjectToFloat(Object floatObj){
        return convertObjectToFloat(floatObj, DEFAULT_FLOAT_VALUE);
    }

    public static float convertObjectToFloat(Object floatObj,float exceptionResult){
        float result=exceptionResult;
        String  floatStr= floatObj==null?null:floatObj.toString();
        if(floatStr==null || "".equals(floatStr.trim())) return exceptionResult;
        try {
            result=Float.parseFloat(floatStr);
        }catch(Exception e){

        }
        return result;
    }

    public static String convertObjectToString(Object strObj){
        return convertObjectToString(strObj, DEFAULT_STRING_VALUE);
    }

    public static String convertObjectToString(Object strObj, String exceptionResult){
        String result = exceptionResult;
        if(strObj==null) return result;
        try {
            result = strObj.toString();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }


//	public static void main(String[] args){
//		System.out.println(ConvertUtil.convertStringToDate("2015-02-10",null));
//		System.err.println(ConvertUtil.keepScaleOfDouble(1.856D));
//		System.err.println(ConvertUtil.keepScaleOfDouble(1.852D));
//		System.err.println(ConvertUtil.keepRoundUpScaleOfDouble(1.856D,2));
//		System.err.println(ConvertUtil.keepRoundUpScaleOfDouble(1.852D,2));
//		System.err.println(ConvertUtil.keepRoundDownScaleOfDouble(1.856D,2));
//		System.err.println(ConvertUtil.keepRoundDownScaleOfDouble(1.852D,2));
//		System.err.println(convertObjectToBoolean("1"));
//		System.err.println(convertObjectToBoolean("false"));
//		System.err.println(convertObjectToBoolean("true"));
//		System.err.println(convertObjectToBoolean("test"));
//
//	}

    public static String IdsListToString(List ids, String exceptionResult){
        if(ids==null||ids.size()==0)return exceptionResult;
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            sb.append(ids.get(i)+",");
        }
        return sb.toString().substring(0, sb.length()-1);
    }

    public static double keepAppointScaleOfDouble(double value, int scale){

        BigDecimal bg = new BigDecimal(value);
        double resValue = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();

        return resValue;
    }
}
