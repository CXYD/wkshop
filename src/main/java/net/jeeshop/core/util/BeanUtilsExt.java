package net.jeeshop.core.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by dylan on 15-9-5.
 */
public class BeanUtilsExt {
    static {
        ConvertUtils.register(new DateConverter(null) ,java.util.Date.class);
    }

    /**
     * 属性复制
     * @param dest 目标
     * @param orig 源
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
