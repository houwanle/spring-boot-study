package com.lele.springbootstudy.common;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author: lele
 * @date: 2022/6/8 19:26
 * @description: 错误代码定义
 */

public class BaseErrorCode {

    /**
     * 通过key从资源文件中获取对应的错误信息描述
     * @param key the key
     * @return message
     */
    public static String getMessage(String key) {
        System.out.println();
        return ResourceBundle.getBundle("i18n/message", LocaleContextHolder.getLocale()).getString(key);
    }

    /**
     * 通过key和指定（EN/US）的资源文件获取对应的错误信息描述
     * @param key    the key
     * @param locale the locale
     * @return message
     */
    public static String getMessage(String key, Locale locale) {
        return ResourceBundle.getBundle("i18n/message", locale).getString(key);
    }


    /**
     * 系统异常
     */
    public final static int SYSTEM_EXCEPTION = 1110;

    /**
     * 参数为空
     */
    public final static int PARAMETER_IS_NULL = 1210;

    /**
     * Response JSON解析异常
     */
    public final static int RESPONSE_JSON_EXCEPTION = 1340;

}
