package me.jmix.brothertakeaway.utils;

import me.jmix.brothertakeaway.enums.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getStateCode())) {
                return each;
            }
        }
        return null;
    }
}
