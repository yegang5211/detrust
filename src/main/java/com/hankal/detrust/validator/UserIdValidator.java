package com.hankal.detrust.validator;

import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;

public class UserIdValidator {
    public static boolean isUserId(String userId) {
        if (userId.length() != 32)
            throw new GlobalException(CodeMsg.DEVICEID_NOTSURPPORT);

        return true;
    }
}
