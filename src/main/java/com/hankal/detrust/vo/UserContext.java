package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModelProperty;

public class UserContext {
    @ApiModelProperty(required = true, value="用户唯一标识，等同于phone，是系统间交互使用内部关联属性",name="userId",example="")
    private String userId;
    @ApiModelProperty(required = true, value="登录标识，指向一个用户登录的相关信息",name="loginId",example="")
    private String loginId;
    @ApiModelProperty(required = true, value="一个抽象的设备类型，device类型包括手机、pad、电脑客户端等\n" +
            "   如果是手机，deviceid是唯一标识\n" +
            "   如果是电脑，网卡mac地址是唯一标识\n",name="deviceId",example="")
    private String deviceId;
    @ApiModelProperty(required = true, value="手机号",name="mobilePhone",example="例如 13800138000")
    private String mobilePhone;
    @ApiModelProperty(required = true, value="一个随机的32位字符串，系统自动生成，每次请求需要带上这个token",name="loginToken",example="")
    private String loginToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
