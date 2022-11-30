package com.lele.springbootstudy.method;

import java.util.UUID;

/**
 * @author: lele
 * @date: 2022/7/22 7:58
 * @description: 系统处理相关的工具类
 */
public class BaseSysMethod {

    /**
     * 创建一个唯一的id
     * @param prefix	前缀
     * @return			创建的最终id
     */
    public static String createUniqueId(String prefix) {
        UUID uuid = UUID.randomUUID();
        String text = uuid.toString().toLowerCase();
        String result = prefix + "_" + text;
        return result;
    }

    /**
     * 创建接口调用的业务id，即接口调用中的公共字段businessId，即类BaseRequestJson中的mBusinessId
     * @return			创建的最终id
     */
    public static String createBusinessId() {
        UUID uuid = UUID.randomUUID();
        String text = uuid.toString().toLowerCase();
        String result = "business_" + text;
        return result;
    }
}
