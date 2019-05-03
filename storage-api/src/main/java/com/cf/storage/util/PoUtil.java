package com.cf.storage.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.cf.storage.core.user.po.UserView;
import com.cf.storage.util.RandomUtil;

public class PoUtil {

    public static boolean existsField(Class<?> clz, String fieldName) {
        try {
            return clz.getDeclaredField(fieldName) != null;
        }
        catch (Exception e) {
        }
        if (clz != Object.class) {
            return existsField(clz.getSuperclass(), fieldName);
        }
        return false;
    }

    public static void setProperty(Object bean, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (existsField(bean.getClass(), name)) {
            BeanUtils.setProperty(bean, name, value);
        }
    }

    public static void setCoreCommonProperty(Object bean) throws IllegalAccessException, InvocationTargetException {
        String id = RandomUtil.get32UUID();
        setProperty(bean, "id", id);
        Date now = new Date();
        setProperty(bean, "createTime", now);
        setProperty(bean, "updateTime", now);
        if(LoginUserUtil.isLogin()){
            UserView ui = LoginUserUtil.getLoginUserInfo();
            setProperty(bean, "creatorId", ui.getId());
        }
    }

    public static <T> T createCorePo(Class<T> clazz)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        T ret = clazz.newInstance();
        setCoreCommonProperty(ret);
        return ret;
    }

    public static <T> T createObject(Class<T> t, String className, Object... initargs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (initargs == null) {
            return createObject(t, className, null, null);
        }
        Class<?>[] parameterTypes = new Class<?>[initargs.length];
        for (int i = 0; i < initargs.length; i++) {
            parameterTypes[i] = initargs[i].getClass();
        }
        return createObject(t, className, parameterTypes, initargs);
    }

    public static <T> T createObject(Class<T> t, String className, Class<?>[] parameterTypes, Object[] initargs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> instanceClass = Class.forName(className);
        if (parameterTypes != null && initargs != null) {
            if (parameterTypes.length == initargs.length) {
                Constructor<?> constructor = instanceClass.getConstructor(parameterTypes);
                return t.cast(constructor.newInstance(initargs));
            }
            else {
                throw new IllegalArgumentException("Argument arrays lengths are not match");
            }
        }
        else if (parameterTypes == null && initargs == null) {
            return t.cast(instanceClass.newInstance());
        }
        else {
            throw new IllegalArgumentException("Argument arrays must be both null or both not null");
        }
    }

}
