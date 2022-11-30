package com.lele.springbootstudy.method;

import com.lele.springbootstudy.common.BaseErrorCode;
import com.lele.springbootstudy.common.ParamsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import com.alibaba.fastjson.JSON;

/**
 * @author: lele
 * @date: 2022/7/26 21:30
 * @description: Map处理相关的工具类
 */
public class BaseMapMethod {
    private static final String TAG = "BaseMapMethod";

    /**
     * 将Map<String, Object>中获取的对象转换为字符串类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的字符串类型的数值
     */
    public static String getToStr(Map<String, Object> map, String key, String defaultValue) {
        if (map == null) {
            return defaultValue;
        }
        String result = defaultValue;

        try {
            Object obj = map.get(key);
            if (obj != null) {
                result = obj.toString().trim();
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToStr(" + key + ") : " + e.getMessage(), e);
        }

        return result;
    }

    /**
     * 将Map<String, Object>中获取的对象转换为Map<String, Object>类型
     * @param map 要处理的对象
     * @param key 要获取的键值
     * @return 获取的键值对应的对象的Map<String, Object>类型的数据
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getToMapObj(Map<String, Object> map, String key) {
        Map<String, Object> result = null;

        try {
            if (map != null && BaseStrMethod.isNotEmpty(key)) {
                Object obj = map.get(key);
                if (obj != null) {
                    result = (Map<String, Object>) obj;
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToMapObj(" + key + ") : " + e.getMessage(), e);
            throw new ParamsException(BaseErrorCode.RESPONSE_JSON_EXCEPTION, BaseErrorCode.getMessage("RESPONSE_JSON_EXCEPTION"), "getToMapObj(" + key + ") : " + e.getMessage());
        }

        if (result == null) {
            result = new HashMap<String, Object>();
        }

        return result;
    }

    /**
     * 将Map<String, Object>中获取的对象转换为整数类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的整数型的数值
     */
    public static Integer getToInt(Map<String, Object> map, String key, Integer defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToInt(result, defaultValue);
        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为整数类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的整数型的数值
     */
    public static int getToIntEx(Map<String, Object> map, String key, int defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToIntEx(result, defaultValue);
        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为Long类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的整数型的数值
     */
    public static Long getToLong(Map<String, Object> map, String key, Long defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToLong(result, defaultValue);

        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为Float类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的整数型的数值
     */
    public static Float getToFloat(Map<String, Object> map, String key, Float defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToFloat(result, defaultValue);

        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为Double类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的整数型的数值
     */
    public static Double getToDouble(Map<String, Object> map, String key, Double defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToDouble(result, defaultValue);
        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为布尔类型
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的布尔类型的数值
     */
    public static boolean getToBool(Map<String, Object> map, String key, boolean defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToBool(result, defaultValue);
        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为Date类型,Date的字符串格式为:yyyy-MM-dd HH:mm:ss
     * @param map          要处理的对象
     * @param key          要获取的键值
     * @param defaultValue 失败时的缺省值
     * @return 获取的键值对应的对象的布尔类型的数值
     */
    public static Date getToFormatDate(Map<String, Object> map, String key, Date defaultValue) {
        String result = getToStr(map, key, "");

        if (BaseStrMethod.isEmpty(result)) {
            return defaultValue;
        } else {
            return BaseStrMethod.strToFormatDate(result, defaultValue);
        }
    }

    /**
     * 将Map<String, Object>中获取的对象转换为链表类型
     * @param map 要处理的对象
     * @param key 要获取的键值
     * @param cls 链表成员类型
     * @return 获取的键值对应的对象的链表的数值
     */
    public static <T> List<T> getToListClass(Map<String, Object> map, String key, Class<T> cls) {
        List<T> result = null;

        try {
            if (map != null && BaseStrMethod.isNotEmpty(key)) {
                Object obj = map.get(key);
                if (obj != null) {
                    String txt = BaseJsonMethod.toStringWithDateWithMapSort(obj);
                    result = JSON.parseArray(txt, cls);
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToListClass(" + key + ") : " + e.getMessage(), e);
        }

        if (result == null) {
            result = new ArrayList<T>();
        }

        return result;
    }


    public static <T> List<T> getToListClassSupportNull(Map<String, Object> map, String key, Class<T> cls) {
        List<T> result = null;

        try {
            if (map != null && BaseStrMethod.isNotEmpty(key)) {
                Object obj = map.get(key);
                if (obj != null) {
                    String txt = BaseJsonMethod.toStringWithDateWithMapSort(obj);
                    result = JSON.parseArray(txt, cls);
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToListClassSupportNull(" + key + ") : " + e.getMessage(), e);
        }

        return result;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getToClass(Map<String, Object> map, String key, Class<T> cls) {
        T result = null;

        try {
            if (map != null && BaseStrMethod.isNotEmpty(key)) {
                Object obj = map.get(key);
                if (obj != null) {
                    result = cls.newInstance();
                    BeanUtils.populate(result, (Map<String, Object>) obj);
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToClassEx(" + key + ") : " + e.getMessage(), e);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getToClassEx(Map<String, Object> map, String key, Class<T> cls) {
        T result = null;

        try {

            if (map != null && BaseStrMethod.isNotEmpty(key)) {
                Object obj = map.get(key);
                if (obj != null) {
                    result = cls.newInstance();
                    ConvertUtils.register(new Converter() {
                        @Override
                        public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                return simpleDateFormat.parse(value.toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }, Date.class);
                    BeanUtils.populate(result, (Map<String, Object>) obj);
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToClassEx(" + key + ") : " + e.getMessage(), e);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getToClassEx(Map<String, Object> map, Class<T> cls) {
        T result = null;

        try {

            if (map != null) {
                result = cls.newInstance();
                ConvertUtils.register(new Converter() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public Object convert(Class type, Object value) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            return simpleDateFormat.parse(value.toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }, Date.class);
                BeanUtils.populate(result, map);
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "getToClassEx : " + e.getMessage(), e);
        }

        return result;
    }


    /**
     * 过滤Map中的成员
     * @param keyList 期望保留的键值数组
     * @param srcMap  源Map
     * @param dstMap  目标Map
     */
    public static void filterMapObj(String[] keyList, Map<String, Object> srcMap, Map<String, Object> dstMap) {
        if (keyList == null || keyList.length <= 0 || srcMap == null || srcMap.size() <= 0 || dstMap == null) {
            BaseLogMethod.logWarn(TAG, "filterMapObj : parameter is invalid");
            return;
        }

        try {
            for (String key : keyList) {
                if (srcMap.containsKey(key)) {
                    dstMap.put(key, srcMap.get(key));
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "filterMapObj : " + e.getMessage(), e);
        }
    }

    /**
     * 过滤Map中的成员
     * @param keyList 期望保留的键值数组
     * @param srcMap  源Map
     * @param dstMap  目标Map
     */
    public static void filterMapStr(String[] keyList, Map<String, String> srcMap, Map<String, String> dstMap) {
        if (keyList == null || keyList.length <= 0 || srcMap == null || srcMap.size() <= 0 || dstMap == null) {
            BaseLogMethod.logWarn(TAG, "filterMapStr : parameter is invalid");
            return;
        }

        try {
            for (String key : keyList) {
                if (srcMap.containsKey(key)) {
                    dstMap.put(key, srcMap.get(key));
                }
            }
        } catch (Exception e) {
            BaseLogMethod.logError(TAG, "filterMapStr : " + e.getMessage(), e);
        }
    }



    /**
     * 从Map<String, Object>中获取List对象，如果没有则创建
     * @param map 要处理的对象
     * @param key 要获取的键值
     * @param cls List的类型
     * @return List对象，并且已经存入map中
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getListWithCreate(Map<String, Object> map, String key, Class<T> cls) {
        if (map == null || BaseStrMethod.isEmpty(key) || cls == null) {
            return null;
        }

        List<T> result = null;
        if (map.containsKey(key)) {
            result = (List<T>) map.get(key);

            if (result == null) {
                BaseLogMethod.logError(TAG, "getListWithCreate : cannot get List : " + key);
            }
        } else {
            result = new ArrayList<T>();
            map.put(key, result);
        }

        return result;
    }
}
