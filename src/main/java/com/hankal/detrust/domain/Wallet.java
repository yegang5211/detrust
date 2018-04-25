package com.hankal.detrust.domain;

import java.util.Date;

public class Wallet {
    private String userId;
    private String phoneNumber;
    private String deviceId;

    private String walletName;
    private String walletAddress;
    private int walletTypeCode;
    private int walletGenerateTypeCode;
    private String passphrase;
    private String privateKey;
    private String keystore;
    private Date createTime;

    private String tokenAddress;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public int getWalletTypeCode() {
        return walletTypeCode;
    }

    public void setWalletTypeCode(int walletTypeCode) {
        this.walletTypeCode = walletTypeCode;
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


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public int getWalletGenerateTypeCode() {
        return walletGenerateTypeCode;
    }

    public void setWalletGenerateTypeCode(int walletGenerateTypeCode) {
        this.walletGenerateTypeCode = walletGenerateTypeCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }
}
