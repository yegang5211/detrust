package com.hankal.detrust.Util;

import java.util.UUID;

/**
 * Created by yegang5211 on 2018/2/11.
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
