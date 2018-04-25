package com.hankal.miaosha.intercepter;

import com.hankal.miaosha.domain.MiaoshaUser;

/**
 * Created by yegang5211 on 2018/2/26.
 */
public class UserContext {

    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }

    public static MiaoshaUser getUser() {
        return userHolder.get();
    }

}
