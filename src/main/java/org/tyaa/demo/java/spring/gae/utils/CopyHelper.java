package org.tyaa.demo.java.spring.gae.utils;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CopyHelper {
    private static final Logger log =
            Logger.getLogger(CopyHelper.class.getName());

    public static <T> void copy(T _from, T _to) {
        try {
            Class<T> _clazz = (Class<T>) _from.getClass();
            Field[] fields = _clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(_to, field.get(_from));
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
        }

    }
}
