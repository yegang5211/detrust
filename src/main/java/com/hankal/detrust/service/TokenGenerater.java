package com.hankal.detrust.service;

import com.hankal.detrust.Util.TokenUtils;
import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;

/*
Token，就是令牌，最大的特点就是随机性，不可预测。一般黑客或软件无法猜测出来。
Token 作用是：

1)防止表单重复提交、
2)跨站点请求伪造

实现方案采用redis生成token，增加过期时间， 手机端调用和web认证端调用分开。
 */
public class TokenGenerater {
    public static String generate(String userId) {
        if (userId == null || userId.length() != 32) {
            throw new GlobalException(CodeMsg.TOKEN_ERR_PARAMETER_USERID_LENGHT);
        }

        return TokenUtils.createJwtToken(userId);
    }

    public void resetAccessToken(String userId){
        generate(userId);
    }

}
