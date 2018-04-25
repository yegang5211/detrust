package com.hankal.detrust.service.walletLogic;

import com.hankal.detrust.domain.Wallet;
import com.hankal.detrust.vo.WalletVo;

import java.util.Date;

public class WalletBuilder {

    public static Wallet Build(WalletVo walletVo, WalletBuildType type) {
        Wallet wallet = new Wallet();
        wallet.setUserId(walletVo.getUserId());
        wallet.setDeviceId(walletVo.getDeviceId());
        wallet.setUserId(wallet.getUserId());
        wallet.setPhoneNumber(walletVo.getMobilePhone());
        wallet.setWalletName(walletVo.getWalletName());
        wallet.setWalletAddress(walletVo.getWalletAddress());
        wallet.setWalletTypeCode(0); // 0-普通钱包、1-智能合约钱包、2-代币合约
        if (type == WalletBuildType.CREATE) {
            wallet.setWalletGenerateTypeCode(0); //钱包生成方式：0-创建 1-导入
        } else {
            wallet.setWalletGenerateTypeCode(1); //钱包生成方式：0-创建 1-导入
        }

        wallet.setTokenAddress("0x000000000000000000000000000000000000000000"); // 默认以太币，TODO：此处需要优化，放到配置里

        wallet.setPassphrase(walletVo.getPassphrase());
        wallet.setPrivateKey(walletVo.getPrivateKey());
        wallet.setKeystore(walletVo.getKeystore());
        wallet.setCreateTime(new Date());

        return wallet;
    }
}
