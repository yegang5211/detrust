package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "钱包创建和导入操作", description = "钱包名称、钱包密码（加密后的）")
public class WalletVo extends RequestVo {
    @ApiModelProperty(required = true, value = "钱包名称", name = "walletName", example = "必填")
    private String walletName;
    @ApiModelProperty(required = true, value = "钱包密码（暂时不存储）", name = "passphrase", example = "必填")
    private String passphrase;

    @ApiModelProperty(required = true, value = "钱包地址", name = "walletAddress", example = "必填")
    private String walletAddress;
    @ApiModelProperty(required = true, value = "钱包账户的私钥（暂时不存储）", name = "privateKey", example = "必填")
    private String privateKey;
    @ApiModelProperty(required = true, value = "钱包账户的私钥算法", name = "keystore", example = "必填")
    private String keystore;
    @ApiModelProperty(required = true, value = "钱包生成方式（0-创建  1-导入）", name = "generateType", example = "必填")
    private int generateType = 0;

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public int getGenerateType() {
        return generateType;
    }

    public void setGenerateType(int generateType) {
        this.generateType = generateType;
    }
}
