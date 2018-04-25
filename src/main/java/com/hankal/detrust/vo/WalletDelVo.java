package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "钱包删除操作", description = "手机号码，设备标识，TOKEN")
public class WalletDelVo extends RequestVo {
    @ApiModelProperty(required = true, value = "钱包地址", name = "walletAddress", example = "必填")
    @NotNull
    private String walletAddress;

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
