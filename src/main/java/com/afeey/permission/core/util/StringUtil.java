package com.afeey.permission.core.util;

/**
 * 字符串常用工具类
 * 
 * @author yjl
 *
 */
public class StringUtil {
    /**
     * 工具类是静态成员的集合，注定不会被实例化。因此，不应该有公共的构造函数。
     * 所以这里定义一个私有的构造函数，限制其实例化。 
     */
    private StringUtil() {}
    
    /**
     * Integer 转 String
     * @param integer Integer
     * @return String
     */
    public static String integer2Str(Integer integer) {
        return (integer == null) ? "" : integer.toString();
    }
    
    /**
     * Long 转 String
     * @param longObj Long
     * @return String
     */
    public static String longObj2Str(Long longObj) {
        return (longObj == null) ? "" : longObj.toString();
    }

    /**
     * Object 转 String
     * @param object Object
     * @return String
     */
    public static String object2Str(Object object) {
        return (null == object) ? "" : object.toString();
    }
    
    /**
     * 判断是否为空
     * @param target Object
     * @return boolean
     */
    public static boolean isBlank(Object target) {
        if (target == null || "".equals(target.toString().trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为空
     * @param target Object
     * @return boolean
     */
    public static boolean isNotBlank(Object target) {
        return !isBlank(target);
    }

    /**
     * 将字符串去除前后空格，如果为空，返回空字符串
     * @param str 输入字符串
     * @return 字符串
     */
    public static String strRemoveNullAndBlank(String str) {
        return str == null ? "" : str.trim();
    }
}
