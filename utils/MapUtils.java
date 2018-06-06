/**
 * Copyright (C) 2016-2017 Hangzhou Elabcare Co. Ltd.
 * All right reserved.
 *
 * @author: Simon.lee
 * date: 2017-03-22 11:22
 */
package com.xjhh.framework.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 工具类
 */
public class MapUtils {
    /**
     * 从 Map 集合中返回属性值
     *
     * @param map
     * @param key
     * @param defaultValue
     * @param <E>
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final static <E> E get(Map map, Object key, E defaultValue) {
        Object o = map.get(key);
        if (o == null) {
            return defaultValue;
        }
        return (E) o;
    }

    /**
     * Map对象转化成 JavaBean对象
     *
     * @param obj
     * @param map
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked", "hiding"})
    public static void map2Java(Object obj, Map<String, Object> map) throws Exception {
        try {
            //创建 Bean 属性
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                for (PropertyDescriptor pd : propertyDescriptors) {
                    //属性名 小写
                    String propertyName = pd.getName().toLowerCase();
                    // 属性值 map也要小写。。。
                    if (map.containsKey(propertyName)) {
                        Object propertyValue = map.get(propertyName);
                        if (propertyValue != null) {
                            pd.getWriteMethod().invoke(obj, propertyValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * JavaBean对象转化成Map对象
     *
     * @param javaBean
     * @return
     * @author jqlin
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map java2Map(Object javaBean) {
        Map map = new HashMap(16);

        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                // javaBean属性名
                String propertyName = null;
                // javaBean属性值
                Object propertyValue = null;
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (!"class".equals(propertyName)) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean);
                        map.put(propertyName, propertyValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}