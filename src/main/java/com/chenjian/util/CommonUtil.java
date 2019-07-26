package com.chenjian.util;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CommonUtil {


    public static final String BIRTHDAY_DATE_FORMAT="yyyy-MM-dd";

    public static String stampToDateStr(long time){
        try{
            Date date=new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        }catch(Exception e){
            return "";
        }
    }

    public static String stampToDateTimeStr(long time){
        try{
            Date date=new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        }catch(Exception e){
            return "";
        }
    }

    public static String stampToDateTimeStr(Timestamp time, DateFormat df){
        try{
            return df.format(time);
        }catch(Exception e){
            return "";
        }
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

    public static double keepAppointScaleOfDouble(double value, int scale){

        BigDecimal bg = new BigDecimal(value);
        double resValue = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();

        return resValue;
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

    public static String convertObjectToHtml(Object content) {
        if(content==null) return "";
        String html = content.toString();
        html = StringUtils.replace(html,"&apos;", "'");
        html = StringUtils.replace(html, "&quot;", "\"");
        html = StringUtils.replace(html, "&nbsp;&nbsp;","\t");// 替换跳格
        html = StringUtils.replace(html,"&nbsp;", " ");// 替换空格
        html = StringUtils.replace(html,  "&lt;","<");
        html = StringUtils.replace(html, "&gt;", ">");
        return html;
    }

    public static Date convertObjectToDate(Object dateObj,Date exceptionResult){
        Date result=exceptionResult;
        String dateStr = dateObj==null?null:dateObj.toString();
        if(dateStr==null || "".equals(dateStr.trim())) return exceptionResult;
        try {
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            result=Date.valueOf(dateStr);
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        return result;
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

    public static String IdsListToString(List ids, String exceptionResult){
        if(ids==null||ids.size()==0)return exceptionResult;
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            sb.append(ids.get(i)+",");
        }
        return sb.toString().substring(0, sb.length()-1);
    }



    public static void main(String[] args){

    }

}
