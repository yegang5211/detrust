package com.hankal.detrust.service;

import com.hankal.detrust.dao.TransDao;
import com.hankal.detrust.dao.UserDao;
import com.hankal.detrust.dao.WalletDao;
import com.hankal.detrust.domain.*;
import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.vo.TransferVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WalletService {
    @Autowired
    WalletDao walletDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TransDao transDao;

    @Transactional
    public Boolean createNewWallet(Wallet wallet) {
        // 检查请求的与用户信息是否有效
        if (!existUser(wallet))
            throw new GlobalException(CodeMsg.WALLET_USERINFO_ERROR);

        try {
            walletDao.insertNewWallet(wallet);
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.SERVER_ERROR, ex);
        }
        return true;
    }

    private boolean existUser(Wallet wallet) {
        Object obj = userDao.exitOnlineUser(wallet.getDeviceId(), wallet.getPhoneNumber(), wallet.getUserId());
        if (obj == null)
            return false;

        return true;
    }

    public Result<Boolean> deleteWallet(String walletAddress) {
        if ("0x000000000000000000000000000000000000000000".equals(walletAddress))
            return Result.error(CodeMsg.WALLET_DEFAULT_NOTALLOWED_DEL);

        walletDao.deleteWallet(walletAddress);
        return Result.success(true);
    }

    public List<WalletAccount> getAllWalletAccounts(String mobilePhone) {
        return walletDao.getAllWalletAccounts(mobilePhone);
    }

    public List<WalletAsset> getAllAssetByPhoneAndWallet(String userId, String walletAddress) {
        return walletDao.getAllAssetByAddress(userId, walletAddress);
    }

    public List<Trans> getAllTransByUserId(String userId, String tokenAddress) {
        return walletDao.getAllTransByTokenAddress(userId, tokenAddress);
    }

    public List<Asset> getAllAssets() {
        return walletDao.getAllAssets();
    }

    public boolean appendAssetToWallet(String walletAddress, String tokenAddress, String userId, BigDecimal balance) {
        try {
            Object result = walletDao.appendAssetToWallet(
                    walletAddress, tokenAddress, userId, balance, new Date());
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.ASSET_ADD_ERR, ex);
        }
        return true;
    }

    public boolean removeAssetToWallet(String walletAddress, String tokenAddress, String userId) {
        Object result = walletDao.removeAssetToWallet(walletAddress, tokenAddress, userId);
        return true;
    }


    public boolean transfer(TransferVo transferVo) {
        transferVo.setCreateTime(new Date());
        transDao.transfer(transferVo);
        return true;
    }

    public List<TransInfo> getTransInfoBytransHash(String transHahs) {
        return transDao.getByTranHash(transHahs);
    }
}
