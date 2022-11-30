package com.lele.springbootstudy.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author: lele
 * @date: 2022/7/26 22:05
 * @description: JSON处理相关的工具类
 */
public class BaseJsonMethod {
    /**
     * 将对象序列化为字符串并将时间转成标准格式
     * @param obj	要序列化的对象
     * @return		序列化后的字符串
     */
    public static String toStringWithDate(Object obj) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
        return JSON.toJSONStringWithDateFormat(obj, dateFormat, feature);
    }

    /**
     * 将对象序列化为字符串并将时间转成标准格式，同时对Map内的字段按名称进行排序
     * @param obj	要序列化的对象
     * @return		序列化后的字符串
     */
    public static String toStringWithDateWithMapSort(Object obj) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONStringWithDateFormat(obj, dateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 将对象序列化为字符串并将时间转成时间戳
     * @param obj	要序列化的对象
     * @return		序列化后的字符串
     */
    public static String toStringWithTimeStamp(Object obj) {
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
        return JSON.toJSONString(obj, feature);
    }
}
