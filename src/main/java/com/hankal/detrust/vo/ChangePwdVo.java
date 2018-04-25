package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "更改登录密码操作", description = "填写手机号和密码")
public class ChangePwdVo extends RequestVo {
    @ApiModelProperty(required = true, value = "旧密码", name = "oldPwd", example = "必填")
    private String oldPwd;
    @ApiModelProperty(required = true, value = "新密码", name = "newPwd", example = "必填")
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
