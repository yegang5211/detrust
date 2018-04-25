package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "钱包资产分配操作", description = "手机号码，设备标识，TOKEN")
public class WalletAssetVo extends RequestVo {
    @ApiModelProperty(required = true, value = "钱包账号地址", name = "walletAddress", example = "必填")
    @NotNull
    private String walletAddress;

    @NotNull
    @ApiModelProperty(required = true, value = "资产（TOKEN）地址", name = "tokenAddress", example = "必填")
    private String tokenAddress;

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }
}
