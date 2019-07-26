package com.chenjian.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtil
 * @Description
 * @Author chenjian
 * @Date 2019/2/27 18:54
 **/
public class StringUtil {


    public static final Pattern MAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    public static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");

    public static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4E00-\\u9FBF][\\u4E00-\\u9FBF(.|·)]{0,13}[\\u4E00-\\u9FBF]$");

    public static final Pattern NICKNAME_PATTERN = Pattern.compile("^((?!\\d{5})[\\u4E00-\\u9FBF(.|·)|0-9A-Za-z_]){2,11}$");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\s\\S]{6,128}$");

    public static final Pattern CODE_PATTERN = Pattern.compile("^0\\d{2,6}$");

    public static final Pattern POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");

    public static final Pattern ID_PATTERN = Pattern.compile("^\\d{6}(\\d{8}|\\d{11})[0-9a-zA-Z]$");

    public static final Pattern BANK_CARD_PATTERN = Pattern.compile("^\\d{16,30}$");

    public static final String UTF_8 = "UTF-8";


    /**
     *
     * @Title: isNotNullTrim
     * @Description: obj不为null且不为空 返回true
     * @param: @param obj
     * @param: @return
     * @return: boolean
     * @throws:
     */
    public static boolean isNotNullTrim(Object obj) {
        if (obj != null && obj.toString().trim().length() > 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * @Title: isNullTrim
     * @Description:  obj为null 或为空 返回true
     * @param: @param obj
     * @param: @return
     * @return: boolean
     * @throws:
     */
    public static boolean isNullTrim(Object obj) {
        if (obj == null || obj.toString().trim().length() == 0) {
            return true;
        }
        return false;
    }


//    public static Long strToLong(Object str, Long errorVal) {
//        if (isNullTrim(str)) return errorVal;
//        try {
//            return Long.valueOf(str.toString());
//        } catch (Exception e) {
//            // TODO: handle exception
//            return errorVal;
//        }
//    }
//
//    public static int strToInt(Object str, int errorVal) {
//        if (str == null || str.toString().trim().length() == 0) return errorVal;
//        try {
//            return Integer.valueOf(str.toString());
//        } catch (Exception e) {
//            // TODO: handle exception
//            return errorVal;
//        }
//    }

    public static String join(Object[] array, String split) {
        if (array.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0)
                sb.append(split);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String join(Collection<?> coll, String split) {
        if (coll.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Object s : coll) {
            if (isFirst) isFirst = false;
            else sb.append(split);
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 手机号中间四位替换成星号
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (validateMobile(mobile)) {
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return mobile;
    }

    /**
     * 手机号中间四位自定义替换
     *
     * @param mobile
     * @param transCode 中间四位目标值 如GXJF 将136GXJF1111
     * @return
     */
    public static String maskMobile(String mobile, String transCode) {
        if (validateMobile(mobile)) {
            transCode = StringUtil.isNullTrim(transCode) ? "****" : transCode;
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", String.format("$1%s$2", transCode));
        }
        return mobile;
    }

    /**
     * 邮箱地址加星号
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (validateEmail(email)) {
            String userName = email.substring(0, email.indexOf("@"));
            int len = userName.length();
            if (len >= 5) {
                int total = len - 3;
                int half = total / 2;
                int start = half;
                int end = len - half;
                if (total % 2 != 0) {
                    end = end - 1;
                }
                StringBuilder sb = new StringBuilder(email);
                for (int i = start; i < end; i++) {
                    sb.setCharAt(i, '*');
                }
                return sb.toString();
            }
        }
        return email;
    }

    /**
     * 账号中间四位 替换 ****
     * @param account
     * @return
     */
    public static String maskTradeAccount(String account) {
        return account.replaceAll("(\\d{7})\\d*(\\d{4})", "$1****$2");
    }


    /**
     * 验证字符串是不是邮箱.
     *
     * @param email 要验证的邮箱
     * @return 是否正确邮箱
     */
    public static boolean validateEmail(String email) {
        if (StringUtil.isNullTrim(email)) {
            return false;
        }
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();
    }

    /**
     * 验证字符串是不是手机号.
     *
     * @param mobile 要验证的手机号
     * @return 是否正确手机号
     */
    public static boolean validateMobile(String mobile) {
        if (StringUtil.isNullTrim(mobile)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(mobile);
        return m.matches();
    }

    /**
     * 验证姓名是否有效.
     *
     * @param name 要验证的姓名
     * @return 是否正确姓名
     */
    public static boolean validateName(String name) {
        if (StringUtil.isNullTrim(name) || name.replaceAll("[^.·]", "").length() > 1) {
            return false;
        }
        Matcher m = NAME_PATTERN.matcher(name);
        return m.matches();
    }

    /**
     * 验证昵称是否有效.
     *
     * @param nickname 要验证的昵称,规则 不能包含5个数字 允许中英文和数字 2-11位
     * @return 是否正确昵称
     */
    public static boolean validateNickname(String nickname) {


        if (StringUtil.isNullTrim(nickname) || nickname.replaceAll("[^0-9]", "").length() > 4) {
            return false;
        }
        Matcher m = NICKNAME_PATTERN.matcher(nickname);
        return m.matches();
    }

    /**
     * 验证密码格式是否有效.
     *
     * @param password 要验证的密码
     * @return 是否正确密码格式
     */
    public static boolean validatePassword(String password) {
        if (StringUtil.isNullTrim(password)) {
            return false;
        }
        Matcher m = PASSWORD_PATTERN.matcher(password);
        return m.matches();
    }

    /**
     * 验证区号是否有效.
     *
     * @param code 要验证的区号
     */
    public static boolean validateCode(String code) {
        if (StringUtil.isNullTrim(code)) {
            return false;
        }
        Matcher m = CODE_PATTERN.matcher(code);
        return m.matches();
    }

    /**
     * 验证邮政编码是否有效.
     *
     * @param postcode 要验证的邮政编码
     * @return 是否正确邮政编码
     */
    public static boolean validatePostcode(String postcode) {
        if (StringUtil.isNullTrim(postcode)) {
            return false;
        }
        Matcher m = POSTCODE_PATTERN.matcher(postcode);
        return m.matches();
    }

    /**
     * 验证银行卡是否有效.
     *
     * @param bankCardNumber 要验证的银行卡号
     * @return 是否正确银行卡号
     */
    public static boolean validateBankCardNumber(String bankCardNumber) {
        if (StringUtil.isNullTrim(bankCardNumber)) {
            return false;
        }
        Matcher m = BANK_CARD_PATTERN.matcher(bankCardNumber);
        return m.matches();
    }


    /**
     * 验证是否为日期
     *
     * @param date
     * @return
     */
    public static boolean validateDate(String date) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }


}
