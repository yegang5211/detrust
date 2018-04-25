package com.hankal.detrust.vo;

import com.hankal.detrust.validator.IsDeviceId;
import com.hankal.detrust.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "退出登录操作", description = "填写手机号和deviceId")
public class LogoutVo {
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
}
