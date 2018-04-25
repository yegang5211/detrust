package com.hankal.detrust.vo;

import com.hankal.detrust.validator.IsDeviceId;
import com.hankal.detrust.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel(value = "登录操作", description = "填写手机号和密码")
public class LoginVo {
    @ApiModelProperty(required = false,
            value = "用户唯一标识，等同于phone，是系统间交互使用内部关联属性",
            name = "userId",
            example = "选填",
            hidden = true)
    private String userId;

    @ApiModelProperty(required = true,
            value = "手机号",
            name = "mobilePhone",
            example = "必填")
    @NotNull
    @IsMobile
    private String mobilePhone;

    @ApiModelProperty(required = true,
            value = "设备标识",
            name = "deviceId",
            example = "必填")
    @NotNull
    @IsDeviceId
    private String deviceId;

    @ApiModelProperty(required = true,
            value = "客户APP的登录密码，传入服务器前必须要加密处理",
            name = "password",
            example = "必填")
    @NotNull
    @Length(min = 6)
    private String password;

    @ApiModelProperty(required = false,
            value = "设备参数，备用字段",
            name = "deviceParams",
            example = "选填")
    private String deviceParams = "";

    @ApiModelProperty(required = false,
            value = "设备类型，0-phone；1-pad；2-desktop，目前只支持phone",
            name = "deviceType",
            example = "选填")
    private String deviceType = "0";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceType() {
        deviceType = "0"; // 强制重设置为0
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceParams() {
        return deviceParams;
    }

    public void setDeviceParams(String deviceParams) {
        this.deviceParams = deviceParams;
    }

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
}
