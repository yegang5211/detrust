package com.hankal.detrust.validator;

import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;

public class DeviceValidator {
    public static boolean isDevice(String deviceId) {
        if (deviceId.length() < 10)
            throw new GlobalException(CodeMsg.DEVICEID_NOTSURPPORT);

        return true;
    }
}
