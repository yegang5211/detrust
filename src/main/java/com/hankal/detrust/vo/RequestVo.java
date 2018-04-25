package com.hankal.detrust.vo;

import com.hankal.detrust.validator.IsDeviceId;
import com.hankal.detrust.validator.IsMobile;
import com.hankal.detrust.validator.IsToken;
import com.hankal.detrust.validator.IsUserId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiModel(value = "请求查询的通用对象" , description = "填写手机号和密码")
public class RequestVo {
    @ApiModelProperty(required = true, value = "访问请求的TOKEN", name = "loginToken", example = "必填")
    @NotNull
    @IsToken
    @Valid
    private String loginToken;

    @ApiModelProperty(required = true, value = "用户标识", name = "userId", example = "必填")
    @NotNull
    @IsUserId
    private String userId;

    @ApiModelProperty(required = true, value = "手机号", name = "mobilePhone", example = "必填")
    @NotNull
    @IsMobile
    private String mobilePhone;

    @ApiModelProperty(required = true, value = "设备标识", name = "deviceId", example = "必填")
    @NotNull
    @IsDeviceId
    private String deviceId;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
