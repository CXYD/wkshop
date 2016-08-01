package net.jeeshop.core.util;

import org.apache.commons.lang.StringUtils;

/**
 * 枚举类型工具类
 * @author dinguangx@163.com
 * @date 2015-12-22 22:38
 */
public class EnumUtils {
    public static <T extends Enum<T>> T parseEnum(Class<T> enumType, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        T type;
        try {
            type = Enum.valueOf(enumType, name);
        } catch (Exception e) {
            return null;
        }
        return type;
    }

}
