package com.hankal.detrust.vo;

import com.hankal.detrust.validator.IsDeviceId;
import com.hankal.detrust.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel(value = "注册用户需要填写的字段", description = "填写跟用户、登录、密码、设备等相关的信息")
public class RegisterVo {
    @ApiModelProperty(required = true, value = "手机号", name = "mobilePhone", example = "必填")
    @NotNull
    @IsMobile
    private String mobilePhone;

    @ApiModelProperty(required = true, value = "客户APP的登录密码", name = "password", example = "必填")
    @NotNull
    @Length(min = 6)
    private String password;

    @ApiModelProperty(required = true, value = "一个抽象的设备类型，device类型包括手机、pad、电脑客户端等\n" +
            "   如果是手机，deviceid是唯一标识\n" +
            "   如果是电脑，网卡mac地址是唯一标识\n", name = "deviceId", example = "必填")
    @NotNull
    @IsDeviceId
    private String deviceId;

    @ApiModelProperty(required = true, value = "注册需要填写的手机验证码，以短信方式发给用户，6位随机数字",
            name = "verifyCode", example = "必填")
    @NotNull
    @Length(min = 6, max = 6)
    private String verifyCode;

    @ApiModelProperty(required = false, value = "设备类型，0-phone；1-pad；2-desktop，目前只支持phone",
            name = "deviceType", example = "选填")
    private String deviceType = "0";

    @ApiModelProperty(required = false, value = "预留字段", name = "deviceParams", example = "选填")
    private String deviceParams;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceParams() {
        if (deviceParams == null) {
            deviceParams = "";
        }
        return deviceParams;
    }

    public void setDeviceParams(String deviceParams) {
        this.deviceParams = deviceParams;
    }

    public String getDeviceType() {
        if (deviceType == null) {
            deviceType = "0";
        }
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
