package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModelProperty;

public class ChangePhoneNumberVo extends RequestVo {
    @ApiModelProperty(required = true, value = "旧手机号", name = "oldPhone", example = "必填")
    private String oldPhone;
    @ApiModelProperty(required = true, value = "新手机号", name = "newPhone", example = "必填")
    private String newPhone;

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhonePhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }
}
