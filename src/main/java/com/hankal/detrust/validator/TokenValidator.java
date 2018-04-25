package com.hankal.detrust.validator;

import com.hankal.detrust.Util.TokenUtils;
import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;
import io.jsonwebtoken.Claims;

import java.util.Calendar;
import java.util.Date;

public class TokenValidator {

    public static boolean isToken(String token) {
        if (foundUser(token) && isExpired(token))
            return true;

        return false;
    }

    public static String getUserId(String token) {
        Claims claims;
        try {
            claims = TokenUtils.parseJWT(token);
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.TOKEN_FORMAT_ERROR, ex);
        }

        return claims.getId();
    }


    private static boolean foundUser(String token) {
        // 检查用户是否存在
        Claims claims;
        try {
            claims = TokenUtils.parseJWT(token);
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.TOKEN_FORMAT_ERROR, ex);
        }

        String userId = claims.getId();
        if (userId == null) {
            throw new GlobalException(CodeMsg.DEVICEID_NOTSURPPORT);
        }


        return true;
    }

    private static boolean isExpired(String accessToken) {
        Claims claims = TokenUtils.parseJWT(accessToken);
        // 检查TOKEN是否过期
        Date date = claims.getExpiration();
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        if (date.compareTo(now) == -1)
            throw new GlobalException(CodeMsg.TOKEN_EXPIRED);

        return true;
    }
}
